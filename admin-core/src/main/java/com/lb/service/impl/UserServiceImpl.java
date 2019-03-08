package com.lb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.base.BusinessException;
import com.lb.base.Constant;
import com.lb.base.PublicResultConstant;
import com.lb.entity.Dictionary;
import com.lb.entity.Menu;
import com.lb.entity.SmsVerify;
import com.lb.entity.User;
import com.lb.entity.UserToRole;
import com.lb.mapper.UserMapper;
import com.lb.service.IDictionaryService;
import com.lb.service.IMenuService;
import com.lb.service.IRoleService;
import com.lb.service.ISmsVerifyService;
import com.lb.service.IUserService;
import com.lb.service.IUserToRoleService;
import com.lb.util.ComUtil;
import com.lb.util.GenerationSequenceUtil;
import com.lb.util.JWTUtil;
import com.lb.util.SmsSendUtil;
import com.lb.util.StringUtils;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserToRoleService userToRoleService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ISmsVerifyService smsVerifyService;

    @Autowired
    private IDictionaryService dictionaryService;

    @Override
    public Map<String, Object> checkMobileAndPassword(JSONObject requestJson) throws Exception{
        // 由于已经使用@ValidationParam注解验证过mobile和password，这里可以放心的使用
        String mobile = requestJson.getString(Constant.LOGIN_NAME);
        // 验证手机号格式
        if(!StringUtils.checkMobileNumber(mobile)){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        LambdaQueryWrapper<User> lqw = new QueryWrapper<User>().lambda()
                .eq(User::getMobile, mobile)
                .eq(User::getStatus, 1);
        User user = this.getOne(lqw);
        if(ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString(Constant.PASSWORD),user.getPassWord())){
            throw new BusinessException(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        //测试websocket用户登录给管理员发送消息的例子  前端代码参考父目录下WebSocketDemo.html
//        noticeService.insertByThemeNo("themeNo-cwr3fsxf233edasdfcf2s3","13888888888");
//        MyWebSocketService.sendMessageTo(JSONObject.toJSONString(user),"13888888888");
        user.setRoleName(null);
        return this.getLoginUserAndMenuInfo(user);
    }

    @Override
    public Map<String, Object> getLoginUserAndMenuInfo(User user) {
        Map<String,Object> result = new HashMap<>();
        // 生成token
        user.setToken(JWTUtil.sign(user.getUserNo(),user.getPassWord()));
        result.put("user",user);
        UserToRole userRole = userToRoleService.getByUserNo(user.getUserNo());
        // 根据角色编码查询菜单权限
        List<Menu> menuList = menuService.getMenuByRoleCode(userRole.getRoleCode());
        if(!ComUtil.isEmpty(menuList)){
            List<Menu> treeMenuList = menuService.treeMenuList(Constant.ROOT_MENU,menuList);
            result.put("menuList",treeMenuList);
            List<Menu> buttonList = menuList.stream()
                    .filter(m -> m.getMenuType() == Constant.TYPE_BUTTON)
                    .collect(Collectors.toList());
            result.put("buttonList",buttonList);
        }
        return result;
    }

    @Override
    public Map<String, Object> checkMobileAndCaptcha(JSONObject requestJson) throws Exception {
        String mobile = requestJson.getString(Constant.LOGIN_NAME);
        if(!StringUtils.checkMobileNumber(mobile)){
            throw new BusinessException(PublicResultConstant.MOBILE_ERROR);
        }
        // 根据手机号去查询用户
        User user = getByMobile(mobile);
        // 用户是否被启用
        if(!ComUtil.isEmpty(user) && user.getStatus() != Constant.ENABLE){
            throw new BusinessException("用户处于未启用状态");
        }
        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
                requestJson.getString("captcha"),
                SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.AUTH.name()));
        if(ComUtil.isEmpty(smsVerifies)){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_ERROR);
        }

        // 查看验证码是否过期
        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
            throw new BusinessException(PublicResultConstant.VERIFY_PARAM_PASS);
        }
        // 如果用户为空 则注册用户并设置默认密码
        if(ComUtil.isEmpty(user)){
            Dictionary passwordDic = dictionaryService.getOneByType(Constant.DICT_DEFAULT_PASSWORD);
            // 查询默认用户角色
            Dictionary roleDict = dictionaryService.getOneByType(Constant.DICT_DEFAULT_REGISTER_ROLE);
            User register = User.builder()
                    .mobile(mobile)
                    .passWord(BCrypt.hashpw(passwordDic.getCode(), BCrypt.gensalt()))
                    .build();
            user = this.register(register,roleDict.getCode());
        }

        return this.getLoginUserAndMenuInfo(user);
    }

    @Override
    public User checkAndRegisterUser(JSONObject requestJson) throws Exception {
        return null;
    }

    @Override
    public User getByMobile(String mobile) {

        return this.getOne(new QueryWrapper<User>()
                .setEntity(User.builder()
                        .mobile(mobile).build()));
    }

    @Override
    public List<User> getByUser(User user) {
        LambdaQueryWrapper<User> lambda = new LambdaQueryWrapper<>();
        lambda.setEntity(user);
        return this.list(lambda);
    }

    @Override
    public User register(User user, String roleCode) {
        user.setUserNo(GenerationSequenceUtil.generateUUID("user"));
        user.setCreateTime(System.currentTimeMillis());
        boolean result = this.save(user);
        if(result){
            UserToRole userToRole = UserToRole.builder()
                    .userNo(user.getUserNo())
                    .roleCode(roleCode)
                    .build();
            userToRoleService.save(userToRole);
        }
        return user;
    }
}

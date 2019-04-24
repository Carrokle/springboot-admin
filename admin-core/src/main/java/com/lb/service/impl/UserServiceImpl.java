package com.lb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.base.BusinessException;
import com.lb.base.Constant;
import com.lb.base.PublicResultConstant;
import com.lb.entity.Menu;
import com.lb.entity.Role;
import com.lb.entity.User;
import com.lb.mapper.UserMapper;
import com.lb.service.IMenuService;
import com.lb.service.IRoleService;
import com.lb.service.IUserService;
import com.lb.util.ComUtil;
import com.lb.util.JWTUtil;

import org.apache.commons.collections4.map.HashedMap;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Override
    public Map<String, Object> checkMobileAndPassword(JSONObject requestJson) {
        return null;
    }

    @Override
    public Map<String, Object> checkMobileAndCaptcha(JSONObject requestJson) {
        return null;
    }

    @Override
    public User checkUsernameAndPassword(JSONObject requestJson) throws BusinessException {
        String username = requestJson.getString("username");
        // 查询用户
        User user = this.getByUsername(username);
        if(ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("password"),user.getPassword())){
            throw new BusinessException(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        if(user.getStatus() != Constant.ENABLE){
            throw  new BusinessException(PublicResultConstant.USER_IS_NOT_ENABLE);
        }
        List<Role> roles = roleService.getByUserId(user.getUserId());
        user.setRoleName(ComUtil.isNotEmpty(roles) ? roles.get(0).getRoleName() : "");
        String token = JWTUtil.sign(String.valueOf(user.getUserId()), user.getPassword());
        user.setToken(token);
        return user;
    }

    @Override
    public String loginByUsername(JSONObject requestJson) throws BusinessException {
        String username = requestJson.getString("username");
        // 查询用户
        User user = this.getByUsername(username);
        if(ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("password"),user.getPassword())){
            throw new BusinessException(PublicResultConstant.INVALID_USERNAME_PASSWORD);
        }
        if(user.getStatus() != Constant.ENABLE){
            throw  new BusinessException(PublicResultConstant.USER_IS_NOT_ENABLE);
        }
        return JWTUtil.sign(String.valueOf(user.getUserId()),user.getPassword());
    }

    @Override
    public User checkAndRegisterUser(JSONObject requestJson) {
        return null;
    }

    @Override
    public User updateForgetPassword(JSONObject requestJson) {
        return null;
    }

    @Override
    public User getByMobile(String mobile) {
        return null;
    }

    @Override
    public User getByUsername(String username) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,username);
        return this.getOne(lqw,false);
    }

    @Override
    public Map<String, Object> getLoginUserAndMenuInfo(User user) {
        Map<String,Object> result = new HashedMap<>();
        result.put("user",user);
        // 查询角色信息
        List<Role> roles = roleService.getByUserId(user.getUserId());
        Set<Menu> menuList = null;
        if(Constant.SUPER_ADMIN_ID.equals(user.getUserId()+2)){
            menuList = new HashSet<>(menuService.list());
        }else{
            Long[] ids = roles.stream().map(Role::getRoleId).distinct().toArray(Long[]::new);
            menuList = menuService.getByRoleIds(ids);
        }
        // Set<String> permissions = menuService.getPermissions(menuList);
        // 设置角色拥有的权限
        // role.setPermissions(permissions);
        // 构造成属性结构
        List<Menu> resultMenuList = ComUtil.isEmpty(menuList) ? null : menuService.treeMenuList(Constant.ROOT_MENU, menuList);
        result.put("menu",resultMenuList);

        result.put("roles",roles);
        return result;
    }
}

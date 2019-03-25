package com.lb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lb.base.Constant;
import com.lb.entity.User;
import com.lb.entity.UserThirdparty;
import com.lb.mapper.UserThirdpartyMapper;
import com.lb.model.ThirdPartyUser;
import com.lb.service.IDictionaryService;
import com.lb.service.IUserService;
import com.lb.service.IUserThirdpartyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 第三方用户表 服务实现类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
@Service
public class UserThirdpartyServiceImpl extends ServiceImpl<UserThirdpartyMapper, UserThirdparty> implements IUserThirdpartyService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDictionaryService dictionaryService;
    @Override
    public User insertThirdPartyUser(ThirdPartyUser param, String password) {
        User sysUser = User.builder()
                .passWord(password)
                .userName("游客" + param.getOpenid())
                .mobile(param.getOpenid())
                .avatar(param.getAvatarUrl())
                .build();
        User user = userService.register(sysUser,dictionaryService.getDefaultRoleCode());
        // 初始化第三方信息
        UserThirdparty thirdparty = UserThirdparty.builder()
                .providerType(param.getProvider())
                .openId(param.getOpenid())
                .createTime(System.currentTimeMillis())
                .userNo(user.getUserNo())
                .status(Constant.ENABLE)
                .accessToken(param.getToken())
                .build();
        this.save(thirdparty);
        return user;
    }
}

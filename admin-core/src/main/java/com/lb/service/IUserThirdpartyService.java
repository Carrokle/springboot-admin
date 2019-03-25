package com.lb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.User;
import com.lb.entity.UserThirdparty;
import com.lb.model.ThirdPartyUser;

/**
 * <p>
 * 第三方用户表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IUserThirdpartyService extends IService<UserThirdparty> {

    User insertThirdPartyUser(ThirdPartyUser param, String password);
}

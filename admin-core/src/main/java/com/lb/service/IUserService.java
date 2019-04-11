package com.lb.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.base.BusinessException;
import com.lb.entity.User;

import java.util.Map;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author null123
 * @since 2019-04-11
 */
public interface IUserService extends IService<User> {

    Map<String,Object> checkMobileAndPassword(JSONObject requestJson);

    Map<String,Object>  checkMobileAndCaptcha(JSONObject requestJson);

    User checkUsernameAndPassword(JSONObject requestJson) throws BusinessException;

    User  checkAndRegisterUser(JSONObject requestJson);

    User updateForgetPassword(JSONObject requestJson);

    User getByMobile(String mobile);

    User getByUsername(String username);

    Map<String,Object> getLoginUserAndMenuInfo(User user);
}

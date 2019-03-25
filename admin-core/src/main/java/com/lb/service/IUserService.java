package com.lb.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lb.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author null123
 * @since 2019-02-27
 */
public interface IUserService extends IService<User> {

    /**
     * 根据请求的手机号和密码核验用户信息
     * @param requestJson
     * @return 返回用户的信息、菜单、角色等信息
     */
    Map<String,Object> checkMobileAndPassword(JSONObject requestJson) throws Exception;

    Map<String,Object> getLoginUserAndMenuInfo(User user);

    Map<String,Object> checkMobileAndCaptcha(JSONObject requestJson)throws Exception;

    User checkAndRegisterUser(JSONObject requestJson)throws Exception;

    /**
     * 根据手机号获取未被删除的用户
     * @param mobile
     * @return
     */
    User getByMobile(String mobile);

    List<User> getByUser(User user);



    /**
     * 注册用户
     * @param user 用户信息
     * @param roleCode 用户角色编号
     * @return
     */
    User register(User user, String roleCode);

    User updateForgetPassword(JSONObject requestJson)throws Exception;
}

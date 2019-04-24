package com.lb.controller;

import com.lb.base.Constant;
import com.lb.base.PublicResultConstant;
import com.lb.config.ResponseHelper;
import com.lb.config.ResponseModel;
import com.lb.entity.User;
import com.lb.service.IMenuService;
import com.lb.service.IUserService;
import com.lb.util.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2019-2-28
 * Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    /**
     * 根据token获取用户信息
     * @param token 从请求头里获取token
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseModel getUserInfo(@RequestHeader(Constant.HTTP_REQUEST_AUTHORIZATION) String token){
        String userId = JWTUtil.getUserNo(token);
        User user = userService.getById(userId);
        if(user == null){
            return ResponseHelper.buildResponseModel(HttpStatus.NO_CONTENT, PublicResultConstant.INVALID_USER);
        }
        return ResponseHelper.buildResponseModel(userService.getLoginUserAndMenuInfo(user));
    }
}

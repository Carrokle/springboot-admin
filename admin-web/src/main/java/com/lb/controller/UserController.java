package com.lb.controller;

import com.lb.config.ResponseHelper;
import com.lb.config.ResponseModel;
import com.lb.entity.User;
import com.lb.service.IUserService;
import com.lb.util.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseModel getUserInfo(String token){
        String userNo = JWTUtil.getUserNo(token);
        User user = userService.getById(userNo);
        return ResponseHelper.buildResponseModel(user);
    }
}

package com.lb.controller;

import com.alibaba.fastjson.JSONObject;
import com.lb.annotation.Log;
import com.lb.annotation.Pass;
import com.lb.annotation.ValidationParam;
import com.lb.config.ResponseHelper;
import com.lb.config.ResponseModel;
import com.lb.entity.User;
import com.lb.service.IUserService;
import com.lb.util.ComUtil;

import org.apache.commons.httpclient.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Date: 2019-3-5
 * Description: 登录接口
 */
@RestController
@Api(description = "身份认证模块")
public class LoginController {
    @Autowired
    private IUserService userService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @ApiOperation(value = "手机密码登录", notes = "body体参数，不需要Authorization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"13888888888\",\"password\":\"123456\"}"
            , required = true, dataType = "String", paramType = "body")
    })
    @PostMapping("/login")
    @Log(action = "SignIn", modelName = "Login", description = "前台登录接口")
    @Pass
    public ResponseModel<Map<String,Object>> login(
        @ValidationParam("mobile,password") @RequestBody JSONObject requestJson ) throws Exception {
        logger.info("调用登录接口=========>" + DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        return ResponseHelper.buildResponseModel(userService.checkMobileAndPassword(requestJson));
    }

    @ApiOperation(value="短信验证码登录", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"13888888888\",\"captcha\":\"5780\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/login/captcha")
    @Log(action="SignInByCaptcha",modelName= "Login",description="前台短信验证码登录接口")
    @Pass
    public ResponseModel<Map<String, Object>> loginByCaptcha(
            @ValidationParam("mobile,captcha")@RequestBody JSONObject requestJson) throws Exception{
        return ResponseHelper.buildResponseModel( userService.checkMobileAndCaptcha(requestJson));
    }



    @ApiOperation(value="手机验证码注册", notes="body体参数,不需要Authorization",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"username\":\"liugh\",\"mobile\":\"13888888888\",</br>" +
                    "\"captcha\":\"5780\",\"password\":\"123456\",</br>\"rePassword\":\"123456\",\"job\":\"java开发\"," +
                    "</br>\"unit(可不传)\":\"xxx公司\"}"
                    , required = true, dataType = "String",paramType="body")
    })
    @PostMapping("/register")
    @Log(action="register",modelName= "Login",description="注册接口")
    @Pass
    public ResponseModel<User> register(@ValidationParam("username,password,rePassword,mobile,captcha,job")
                                        @RequestBody JSONObject requestJson)throws Exception {
        return ResponseHelper.buildResponseModel( userService.checkAndRegisterUser(requestJson));
    }

    @ApiOperation(value = "忘记密码", notes = "body体参数，不需要Authorization", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\",</br>" +
                    "\"password\":\"123456\",\"rePassword\":\"123456\"}",required = true,
                    dataType = "String", paramType = "body")
    })
    @PostMapping("/forget/password")
    @Pass
    public ResponseModel<User> resetPassword(@ValidationParam("mobile,captcha,password,rePassword")
                                                     @RequestBody JSONObject requestJson) throws Exception {
        return ResponseHelper.buildResponseModel(userService.updateForgetPassword(requestJson));
    }

    /**
     * 检查用户是否注册过
     * @param mobile
     * @return
     */
    @ApiOperation(value = "检验用户是否注册过")
    @ApiImplicitParam(name = "mobile" )
    @GetMapping("/check/mobile")
    @Pass
    public ResponseModel checkRegister(@RequestParam("mobile") String mobile){
        User user = userService.getByMobile(mobile);
        return ResponseHelper.buildResponseModel(!ComUtil.isEmpty(user));
    }
}

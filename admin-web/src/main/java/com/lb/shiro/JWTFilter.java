package com.lb.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lb.base.Constant;
import com.lb.base.PublicResultConstant;
import com.lb.config.ResponseHelper;
import com.lb.entity.User;
import com.lb.service.IUserService;
import com.lb.service.SpringContextBeanService;
import com.lb.util.ComUtil;
import com.lb.util.JWTUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 2019-3-1
 * Description:代码的执行流程preHandle->isAccessAllowed->isLoginAttempt->executeLogin
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private IUserService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        String authorization = httpServletRequest.getHeader("Authorization");
        if (verificationPassAnnotation(request, response, httpServletRequest, authorization)){
            return true;
        }
        if(ComUtil.isEmpty(authorization)){
            responseError(request, response);
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 这里详细说明下为什么最终返回的都是true，即允许访问
     * 例如我们提供一个地址 GET /article
     * 登入用户和游客看到的内容是不同的
     * 如果在这里返回了false，请求会被直接拦截，用户看不到任何东西
     * 所以我们在这里返回true，Controller中可以通过 subject.isAuthenticated() 来判断用户是否登入
     * 如果有些资源只有登入用户才能访问，我们只需要在方法上面加上 @RequiresAuthentication 注解即可
     * 但是这样做有一个缺点，就是不能够对GET,POST等请求进行分别过滤鉴权(因为我们重写了官方的方法)，但实际上对应用影响不大
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if(isLoginAttempt(request,response)){
            try {
                executeLogin(request,response);
            } catch (Exception e) {
                e.printStackTrace();
                responseError(request,response);
            }
        }
        return true;
    }

    /**
     * 判断用户是否想要登录，检测header里是否包含Authorization
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("Authorization");
        return authorization != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("Authorization");
        JWTToken token = new JWTToken(authorization);
        // 提交给realm进行登录，如果错误则会排除异常并捕获
        getSubject(request,response).login(token);
        // 如果没有抛出异常则标识登录成功，返回true
        setUserBean(request,response,token);
        return true;
    }


    /**
     *验证请求方法是否有@Pass注解,有则直接放行
     * @param request
     * @param response
     * @param httpServletRequest
     * @param authorization
     * @return
     * @throws Exception
     */
    private boolean verificationPassAnnotation(
            ServletRequest request, ServletResponse response,
            HttpServletRequest httpServletRequest, String authorization) throws Exception{
        for (String urlMethod : Constant.METHOD_URL_SET) {
            String[] split = urlMethod.split(":--:");
            String reqUrl = split[0];
            String reqMethod = split[1];
            // 是否包含@Pass注解
            boolean isPass = reqUrl.equals(httpServletRequest.getRequestURI())
                    && (reqMethod.equals(httpServletRequest.getMethod()) || "RequestMapping".equals(reqMethod));
            //REST风格URL参数单独切割处理
            boolean isPass2 = StringUtils.countMatches(urlMethod, "{") > 0 &&
                    StringUtils.countMatches(urlMethod, "/") == StringUtils.countMatches(reqUrl, "/")
                    && (reqMethod.equals(httpServletRequest.getMethod()) || "RequestMapping".equals(reqMethod));

            if (isPass || isPass2) {
                Constant.isPass.set(true);
                //判断是否带有authorization
                if (ComUtil.isEmpty(authorization)) {
                    //如果当前url不需要认证，则注入当前登录用户时，给一个空的
                    httpServletRequest.setAttribute(Constant.CURRENT_USER, new User());
                    return true;
                } else {
                    super.preHandle(request, response);
                }
            } else {
                Constant.isPass.set(false);
            }

        }
        return false;
    }

    /**
     * 判断路径参数的url是否和controller方法url一致
     * @param localUrl
     * @param requestUrl
     * @return
     */
    private boolean isSameUrl(String localUrl,String requestUrl){
        String[] tempLocalUrls = localUrl.split("/");
        String[] tempRequestUrls = requestUrl.split("/");
        if(tempLocalUrls.length != tempRequestUrls.length){
            return false;
        }
        StringBuilder sbLocalUrl =new StringBuilder();
        StringBuilder sbRequestUrl =new StringBuilder();
        for (int i = 0; i < tempLocalUrls.length; i++) {
            if(StringUtils.countMatches(tempLocalUrls[i], "{") > 0){
                tempLocalUrls[i]="*";
                tempRequestUrls[i]="*";
            }
            sbLocalUrl.append(tempLocalUrls[i]).append("/");
            sbRequestUrl.append(tempRequestUrls[i]).append("/");
        }
        return sbLocalUrl.toString().trim().equals(sbRequestUrl.toString().trim());
    }


    private void setUserBean(ServletRequest request,ServletResponse response, JWTToken token){
        if(this.userService == null){
            this.userService = SpringContextBeanService.getBean(IUserService.class);
        }
        String userNo = JWTUtil.getUserNo(token.getPrincipal().toString());
        User user = userService.getById(userNo);
        request.setAttribute(Constant.CURRENT_USER,user);
    }

    /**
     * 非法url返回身份错误信息
     * @param request
     * @param response
     */
    private void responseError(ServletRequest request, ServletResponse response){
        PrintWriter out = null;

        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            response.setContentType("application/json; charset=utf-8");
            out.print(JSONObject.toJSONString(ResponseHelper.validationFailure(PublicResultConstant.UNAUTHORIZED)));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.close();
            }
        }
    }
}

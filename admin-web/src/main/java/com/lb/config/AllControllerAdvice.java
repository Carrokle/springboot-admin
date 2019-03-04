package com.lb.config;

import com.lb.base.BusinessException;
import com.lb.base.PublicResultConstant;
import com.lb.exception.ParamJsonException;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * Date: 2019-2-28
 * Description:Controller统一异常处理
 */
@ControllerAdvice
public class AllControllerAdvice {
    private static Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){}


    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){}

    /**
     * 全局异常捕捉处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<String> errorHandler(Exception e){
        e.printStackTrace();
        logger.error("接口出现严重异常：{}",e.getMessage());
        return ResponseHelper.validationFailure(PublicResultConstant.FAILED);
    }


    /**
     * 捕捉捕捉UnauthorizedException
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ResponseModel<String> handleUnauthorized(){
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMISSION);
    }

    /**
     * 捕捉shiro异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public ResponseModel<String> handleShiroException(ShiroException e){
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMISSION);
    }

    /**
     * 捕捉BusinessException自定义抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseModel handleBusinessException(BusinessException e){
        if(e != null){
            logger.error("数据操作失败：" + e.getMessage());
            return ResponseHelper.validationFailure("数据操作失败：" + e.getMessage());
        }
        return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TemplateInputException.class)
    @ResponseBody
    public ResponseModel<String> handleTemplateInputException(TemplateInputException e) {
        return ResponseHelper.validationFailure(PublicResultConstant.USER_NO_PERMISSION);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ParamJsonException.class)
    @ResponseBody
    public ResponseModel<String> handleParamJsonException(Exception e){
        if(e instanceof ParamJsonException){
            logger.error("参数错误：" + e.getMessage());
            return ResponseHelper.validationFailure("参数错误：" + e.getMessage());
        }
        return ResponseHelper.validationFailure(PublicResultConstant.ERROR);
    }

}

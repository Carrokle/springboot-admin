package com.lb.config;

import com.lb.annotation.AccessLimit;
import com.lb.annotation.Log;
import com.lb.annotation.ParamXssPass;
import com.lb.annotation.ValidationParam;
import com.lb.aspect.AccessLimitAspect;
import com.lb.aspect.AspectApi;
import com.lb.aspect.AspectApiImpl;
import com.lb.aspect.ParamXssPassAspect;
import com.lb.aspect.RecordLogAspect;
import com.lb.aspect.ValidationParamAspect;
import com.lb.util.ComUtil;
import com.lb.util.StringUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Date: 2019-2-28
 * Description:Controller切面：防止xss攻击 记录log 参数验证
 */
@Aspect
@Configuration
public class ControllerAspect {

    @Pointcut("execution(* com.lb.controller..*(..))")
    public void aspect(){}

    @Around(value = "aspect()")
    public Object validationPoint(ProceedingJoinPoint pjb) throws Throwable {
        Method method = currentMethod(pjb,pjb.getSignature().getName());
        // 创建被装饰者
        AspectApi aspectApi = new AspectApiImpl();
        // 是否需要验证参数
        if(!ComUtil.isEmpty(StringUtils.getMethodAnnotationOne(method, ValidationParam.class.getSimpleName()))){
            new ValidationParamAspect(aspectApi).doHandlerAspect(pjb,method);
        }

        // 是否需要限流
        if(method.isAnnotationPresent(AccessLimit.class)){
            new AccessLimitAspect(aspectApi).doHandlerAspect(pjb,method);
        }

        // 是否需要拦截xss攻击
        if(method.isAnnotationPresent(ParamXssPass.class)){
            new ParamXssPassAspect(aspectApi).doHandlerAspect(pjb,method);
        }

        // 是否需要记录日志
        if(method.isAnnotationPresent(Log.class)){
            return new RecordLogAspect(aspectApi).doHandlerAspect(pjb,method);
        }
        return pjb.proceed(pjb.getArgs());
    }


    /**
     * 获取目标类的所有方法，找到当前需要执行的方法
     * @param joinPoint
     * @param methodName 方法名
     * @return
     */
    private Method currentMethod(ProceedingJoinPoint joinPoint, String methodName){
        Method[] methods = joinPoint.getTarget().getClass().getMethods();
        Method resultMethod = null;
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }
}

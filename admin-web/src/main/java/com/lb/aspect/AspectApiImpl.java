package com.lb.aspect;

import com.lb.base.Constant;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * Date: 2019-2-28
 * Description:基本被装饰类，做一些公共处理
 */
public class AspectApiImpl  implements AspectApi{
    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        Constant.isPass.set(false);
        return null;
    }
}

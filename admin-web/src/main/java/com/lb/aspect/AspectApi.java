package com.lb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * Date: 2019-2-28
 * Description: 装饰器模式
 */
public interface AspectApi {
    Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable;
}

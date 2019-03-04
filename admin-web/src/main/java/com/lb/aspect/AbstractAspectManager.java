package com.lb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * Date: 2019-2-28
 * Description:
 */
public abstract class AbstractAspectManager implements AspectApi{
    private AspectApi aspectApi;

    public AbstractAspectManager(AspectApi aspectApi) {
        this.aspectApi = aspectApi;
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return this.aspectApi.doHandlerAspect(pjp,method);
    }

    protected abstract Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable;

}

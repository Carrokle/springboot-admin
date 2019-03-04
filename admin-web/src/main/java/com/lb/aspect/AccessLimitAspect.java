package com.lb.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.lb.annotation.AccessLimit;
import com.lb.base.BusinessException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2019-2-28
 * Description: 限流切面
 */
@Slf4j
public class AccessLimitAspect extends AbstractAspectManager{

    /**
     * 添加速率，保证是单例的
     */
    private static RateLimiter rateLimiter = RateLimiter.create(1000);
    /**
     * 使用url作为key,存放令牌桶 防止每次重新创建令牌桶
     */
    private static Map<String,RateLimiter> limiterMap = Maps.newConcurrentMap();

    public AccessLimitAspect(AspectApi aspectApi) {
        super(aspectApi);
    }

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {
        return super.doHandlerAspect(pjp, method);
    }

    @Override
    protected Object execute(ProceedingJoinPoint pjp, Method method) throws Throwable {
        AccessLimit lxRateLimit = method.getAnnotation(AccessLimit.class);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 获取url(存在map集合的key)
        String url = request.getRequestURI();
        if(!limiterMap.containsKey(url)){
            // 创建令牌桶
            rateLimiter = RateLimiter.create(lxRateLimit.perSecond());
            limiterMap.put(url,rateLimiter);
            log.info("<<================ 请求{},创建令牌通，容量{} 成功！！！",url,lxRateLimit.perSecond());
        }
        rateLimiter = limiterMap.get(url);
        if(!rateLimiter.tryAcquire(lxRateLimit.timeOut(), lxRateLimit.timeOutUnit())){
            // 获取令牌
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.info("Error --- 时间:{}，或区域令牌失败",sdf.format(new Date()));
            throw new BusinessException("服务器繁忙，请稍后再试");
        }
        return null;
    }
}

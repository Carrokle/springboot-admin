package com.lb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * Date: 2019-2-28
 * Description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 每秒向桶中放入令牌的数量 默认最大即不限流
     * @return
     */
    double perSecond() default Double.MAX_VALUE;

    /**
     * 获取令牌的等待时间 默认0
     * @return
     */
    int timeOut() default 0;

    /**
     * 超时时间单位
     * @return
     */
    TimeUnit timeOutUnit() default TimeUnit.MILLISECONDS;
}

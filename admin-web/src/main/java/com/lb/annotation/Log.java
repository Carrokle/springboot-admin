package com.lb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2019-2-28
 * Description: 在Controller方法上加入此注解会自动记录日志
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     * @return
     */
    String modelName() default "";

    /**
     * 操作
     * @return
     */
    String action() default "";

    /**
     * 描述
     * @return
     */
    String description() default "";
}

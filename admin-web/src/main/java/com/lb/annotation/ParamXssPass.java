package com.lb.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Date: 2019-2-28
 * Description:在Controller方法上加入该注解不会转义参数，
 *  *  如果不加该注解则会：<script>alert(1)</script> --> &lt;script&gt;alert(1)&lt;script&gt;
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamXssPass {
}

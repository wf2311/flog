package com.wf2311.log.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author wf2311
 * @time 2016/06/03 16:59.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 名称
     */
    String value() default "";

    /**
     * 日志等级
     */
    String level() default "";

    /**
     * 要求用户处于登录状态
     */
    boolean login() default true;

    /**
     * 额外的参数
     */
    String[] params() default "";
}

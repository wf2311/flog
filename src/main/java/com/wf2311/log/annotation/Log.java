package com.wf2311.log.annotation;

import com.wf2311.log.Type;

import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author wf2311
 * @date 2016/06/03 16:59.
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
    Type grade() default Type.NORMAL;


}

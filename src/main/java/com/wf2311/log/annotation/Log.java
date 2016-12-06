package com.wf2311.log.annotation;

import com.wf2311.log.Grade;

import java.lang.annotation.*;

/**
 * The interface Log.
 *
 * @author wf2311
 * @date 2016 /06/03 16:59.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {


    String value() default "";

    /**
     * 认真类型
     *
     * @return
     */
    Grade grade() default Grade.NORMAL;


}

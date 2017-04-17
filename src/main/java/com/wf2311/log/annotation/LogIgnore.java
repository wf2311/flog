package com.wf2311.log.annotation;

import java.lang.annotation.*;

/**
 * @author wf2311
 * @time 2016/12/12 17:15.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogIgnore {
}

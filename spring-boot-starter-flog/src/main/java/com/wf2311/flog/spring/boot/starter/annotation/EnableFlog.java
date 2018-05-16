package com.wf2311.flog.spring.boot.starter.annotation;

import com.wf2311.flog.spring.boot.starter.configuration.FlogAspectAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @date 2018/5/16 10:59.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({FlogAspectAutoConfiguration.class})
public @interface EnableFlog {
}

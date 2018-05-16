package com.wf2311.flog.spring.boot.starter.configuration;

import com.wf2311.log.DefaultLogAspect;
import com.wf2311.log.LogRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @date 2018/5/16 10:54.
 */
@Configuration
@ConditionalOnBean(LogRecord.class)
public class FlogAspectAutoConfiguration {


    @Bean
    public DefaultLogAspect logAspect() {
        return new DefaultLogAspect();
    }

}

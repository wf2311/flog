package com.wf2311.flog.example.config;

import com.wf2311.log.DefaultLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:wf2311@163.com">wf2311</a>
 * @date 2018/5/16 09:58.
 */
@Configuration
public class LogConfig {

    @Bean
    public DefaultLogAspect logAspect() {
        return new DefaultLogAspect();
    }
}

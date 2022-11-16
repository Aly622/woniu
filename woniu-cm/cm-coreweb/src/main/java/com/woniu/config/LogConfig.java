package com.woniu.config;

import com.woniu.log.aspect.LogAspect;
import com.woniu.log.event.LogListener;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName LogConfig.java
 * @Description TODO
 * @createTime 2022年02月24日 21:23:00
 */
@EnableAsync
public class LogConfig {
    @Bean
    public LogListener sysLogListener() {
        return new LogListener();
    }
    @Bean
    public LogAspect sysLogAspect() {
        return new LogAspect();
    }
}

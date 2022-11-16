package com.woniu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yu.liu
 * @version 1.0.0
 * @ClassName NacosConfig.java
 * @Description rabbit配置文件
 * @createTime 2022年02月22日 10:21:00
 */
@Configuration
public class RabbitConfig {
    @Value("${rabbitmq:port}")
    private String port;

    @Value("${rabbitmq:username}")
    private String username;

    @Value("${rabbitmq:password}")
    private String password;

    @Value("${rabbitmq:host}")
    private String host;


    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }
}

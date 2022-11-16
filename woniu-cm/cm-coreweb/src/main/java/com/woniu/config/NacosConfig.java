package com.woniu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yu.liu
 * @version 1.0.0
 * @ClassName NacosConfig.java
 * @Description nacos配置文件
 * @createTime 2022年02月22日 10:46:00
 */
@Configuration
public class NacosConfig {
    @Value("${nacos:weight}")
    private String weight;

    @Value("${nacos:lbstrategy}")
    private String lbstrategy;

    @Value("${nacos:naminguserpc}")
    private String naminguSerpc;

    @Value("${nacos:serveraddresses:0}")
    private String serverAddresse;

    @Value("${nacos:namespace}")
    private String nameSpace;

    @Value("${nacos:hostname}")
    private String hostname;

    @Value("${nacos:username}")
    private String username;

    @Value("${nacos:password}")
    private String password;



    public String getWeight() {
        return weight;
    }

    public String getLbstrategy() {
        return lbstrategy;
    }

    public String getNaminguSerpc() {
        return naminguSerpc;
    }

    public String getServerAddresse() {
        return serverAddresse;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

package com.woniu;

import com.woniu.config.DynamicsValConfig;
import com.woniu.config.JwtAuthConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient//注册到注册中心
@EnableConfigurationProperties({DynamicsValConfig.class, JwtAuthConfig.class})
public class UCApplication {
    public static void main(String[] args) {
        //阿波罗配置中心地址
        System.setProperty("apollo.configService", "http://10.100.20.156:8080");
        System.setProperty("apollo.port", "8080");
        if("k8s".equals(System.getenv("DIAP_SRV_REGISTER"))){
            System.setProperty("spring.cloud.nacos.discovery.enabled","false");
        }
        SpringApplication.run(UCApplication.class, args);
    }
}

package com.woniu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties({JwtAuthConfig.class})
public class GWApplication {
	public static void main(String[] args) {
		//阿波罗配置中心地址
		System.setProperty("apollo.configService", "http://10.100.20.156:8080");
		System.setProperty("apollo.port", "8080");
		System.setProperty("csp.sentinel.app.type", "1");//网关限流
		if("k8s".equals(System.getenv("DIAP_SRV_REGISTER"))){
			System.setProperty("spring.cloud.nacos.discovery.enabled","false");
		}
		SpringApplication.run(GWApplication.class, args);
	}
}

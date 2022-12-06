package com.woniu.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 描述：
 *
 * @author 含光
 * @email jarvan_best@163.com
 * @date 2021/2/25 3:53 下午
 * @company 数海掌讯
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
@EnableDiscoveryClient
public class BusinessServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(BusinessServiceApp.class, args);
    }
}

package com.woniu.config;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName GatewayConfig.java
 * @Description TODO
 * @createTime 2022年04月26日 23:14:00
 */

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.woniu.handler.SentinelFallbackHandler;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 网关限流配置
 */
@Configuration
public class GatewayConfig
{
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelFallbackHandler sentinelGatewayExceptionHandler()
    {
        return new SentinelFallbackHandler();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter()
    {
        return new SentinelGatewayFilter();
    }
}

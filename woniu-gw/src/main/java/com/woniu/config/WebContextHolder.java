package com.woniu.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

/**
 * 从Mono中获取上下文
 */
public class WebContextHolder {
    public static Mono<ServerHttpRequest> getRequest() {
        return Mono.subscriberContext()
                .map(ctx -> ctx.get(ServerHttpRequest.class));
    }
    public static Mono<ServerHttpResponse> getResponse() {
        return Mono.subscriberContext()
                .map(ctx -> ctx.get(ServerHttpResponse.class));
    }
}

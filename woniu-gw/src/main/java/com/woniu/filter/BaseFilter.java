package com.woniu.filter;

import com.alibaba.fastjson.JSONObject;
import com.woniu.response.IResponseCode;
import com.woniu.response.WebResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">xiaojie.li</a>
 * <br/>Created in 2020/2/4
 */
public class BaseFilter {
    protected static final Integer JWT_ORDER = -100;

    protected Mono<Void> responseError(ServerHttpResponse resp, IResponseCode code, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        WebResponse r = WebResponse.fail(code, msg);
        String resString = JSONObject.toJSONString(r);
        DataBuffer buffer = resp.bufferFactory().wrap(resString.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    protected Mono<Void> responseSuccess(ServerHttpResponse resp, IResponseCode code, String msg) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        WebResponse r = WebResponse.fail(code, msg);
        String resString = JSONObject.toJSONString(r);
        DataBuffer buffer = resp.bufferFactory().wrap(resString.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    protected Mono<Void> response(ServerHttpResponse resp, IResponseCode code) {
        resp.setStatusCode(HttpStatus.OK);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        WebResponse r = new WebResponse(code);
        String resString = JSONObject.toJSONString(r);
        DataBuffer buffer = resp.bufferFactory().wrap(resString.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    protected Mono<Void> responseNormalError(ServerHttpResponse resp, IResponseCode code, String msg) {
        resp.setStatusCode(HttpStatus.ACCEPTED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        WebResponse r = WebResponse.fail(code, msg);
        String resString = JSONObject.toJSONString(r);
        DataBuffer buffer = resp.bufferFactory().wrap(resString.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }
}

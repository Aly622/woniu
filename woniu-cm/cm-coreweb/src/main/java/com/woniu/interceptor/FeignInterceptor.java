package com.woniu.interceptor;

/**
 * @创建人 Oliver.Liu
 * @创建时间 3/23/2020
 * @描述
 */

import com.woniu.contants.CommonConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Feign配置
 * 使用FeignClient进行服务间调用，传递headers信息
 */
@Slf4j
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.debug("请求属性 {}", attributes);
        if(attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            log.debug("请求头 USER_ID {}", request.getHeader(CommonConstants.USER_ID));
            //添加token
            requestTemplate.header(CommonConstants.USER_ID, request.getHeader(CommonConstants.USER_ID));
        }
    }
}
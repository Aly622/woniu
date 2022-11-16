package com.woniu.client.factory;

import com.woniu.client.UcTenantClient;
import com.woniu.client.fallback.UcTenantClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UcTenantClientFallbackFactory implements FallbackFactory<UcTenantClient> {
    @Override
    public UcTenantClient create(Throwable throwable) {
        UcTenantClientFallbackImpl ucTenantClientFallback = new UcTenantClientFallbackImpl();
        ucTenantClientFallback.setCause(throwable);
        return ucTenantClientFallback;
    }
}

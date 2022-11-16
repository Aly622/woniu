package com.woniu.client.factory;

import com.woniu.client.UcTenantWchatRelationClient;
import com.woniu.client.fallback.UcTenantWchatRelationClientFallbackImpl;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UcTenantWchatRelationClientFallbackFactory implements FallbackFactory<UcTenantWchatRelationClient> {
    @Override
    public UcTenantWchatRelationClient create(Throwable throwable) {
        UcTenantWchatRelationClientFallbackImpl ucTenantWchatRelationClientFallback = new UcTenantWchatRelationClientFallbackImpl();
        ucTenantWchatRelationClientFallback.setCause(throwable);
        return ucTenantWchatRelationClientFallback;
    }
}

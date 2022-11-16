package com.woniu.client.fallback;

import com.woniu.client.UcTenantClient;
import com.woniu.dto.TenantInfoDTO;
import com.esmartwave.niumeng.diap.response.WebResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UcTenantClientFallbackImpl implements UcTenantClient {
    @Setter
    private Throwable cause;

    @Override
    public WebResponse<List<TenantInfoDTO>> getAllTenant() {
        log.error("#### feign 获取所有租户失败", cause);
        return null;
    }
}

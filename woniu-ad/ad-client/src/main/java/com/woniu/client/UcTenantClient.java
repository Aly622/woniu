package com.woniu.client;


import com.woniu.client.factory.UcTenantClientFallbackFactory;
import com.esmartwave.niumeng.diap.contants.ServiceNameConstants;
import com.woniu.dto.TenantInfoDTO;
import com.esmartwave.niumeng.diap.response.WebResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = ServiceNameConstants.DIAP_USERCENTER, fallbackFactory = UcTenantClientFallbackFactory.class)
public interface UcTenantClient {

    /**
     * 租户下拉列表
     * @return
     */
    @GetMapping("/" + ServiceNameConstants.DIAP_USERCENTER +"/v1/tenant/getAllTenant")
    public WebResponse<List<TenantInfoDTO>> getAllTenant();
}

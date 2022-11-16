package com.woniu.client;

import com.woniu.client.factory.UcTenantWchatRelationClientFallbackFactory;
import com.esmartwave.niumeng.diap.contants.ServiceNameConstants;
import com.woniu.entity.UcTenantWchatRelation;
import com.esmartwave.niumeng.diap.response.WebResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = ServiceNameConstants.DIAP_USERCENTER, fallbackFactory = UcTenantWchatRelationClientFallbackFactory.class)
public interface UcTenantWchatRelationClient {

    /**
     * 获取租户下微信列表
     * @return
     */
    @GetMapping("/" + ServiceNameConstants.DIAP_USERCENTER +"/v1/ucTenantWchatRelation/getAuthorizaWechatList")
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatList();

    /**
     * 根据租户id获取微信列表
     * @param tenantId
     * @return
     */
    @GetMapping("/" + ServiceNameConstants.DIAP_USERCENTER +"/v1/ucTenantWchatRelation/getAuthorizaWechatByTenantId/{tenantId}")
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatByTenantId(@PathVariable("tenantId")Long tenantId);
}

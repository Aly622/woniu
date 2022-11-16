package com.woniu.client.fallback;

import com.woniu.client.UcTenantWchatRelationClient;
import com.woniu.entity.UcTenantWchatRelation;
import com.esmartwave.niumeng.diap.response.WebResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UcTenantWchatRelationClientFallbackImpl implements UcTenantWchatRelationClient {

    @Setter
    private Throwable cause;

    @Override
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatList() {
        log.error("#### feign 获取租户下微信列表失败", cause);
        return null;
    }

    @Override
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatByTenantId(Long tenantId) {
        log.error("#### feign 根据租户id获取租户列表失败：{}", ToStringBuilder.reflectionToString(tenantId), cause);
        return null;
    }
}

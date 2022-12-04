package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.woniu.dao.SuperMapper;
import com.woniu.entity.UcTenantWchatRelation;

import java.util.List;

public interface TenantWchatRelationMapper extends SuperMapper<UcTenantWchatRelation> {

    @InterceptorIgnore(tenantLine = "true")
    void delByTenantId(Long tenantId);

    @InterceptorIgnore(tenantLine = "true")
    List<UcTenantWchatRelation> getAuthorizaWechatByTenantId(Long tenantId);
}

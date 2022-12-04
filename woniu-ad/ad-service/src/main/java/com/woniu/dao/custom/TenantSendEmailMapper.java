package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.woniu.dao.SuperMapper;
import com.woniu.entity.UcTenantSendEmail;

import java.util.List;


public interface TenantSendEmailMapper extends SuperMapper<UcTenantSendEmail> {

    @InterceptorIgnore(tenantLine = "true")
    void delByTenantId(Long tenantId);

    @InterceptorIgnore(tenantLine = "true")
    List<UcTenantSendEmail> getEmailByTenantId(Long tenantId);
}

package com.woniu.service;

import com.woniu.entity.UcTenant;

public interface TenantService {
    boolean saveTenant(UcTenant ucTenant) throws Exception;

    boolean updateTenant(UcTenant ucTenant);
}

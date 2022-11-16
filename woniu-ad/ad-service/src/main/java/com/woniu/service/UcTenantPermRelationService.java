package com.woniu.service;

import com.woniu.entity.UcTenantPermRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.TenantPermVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcTenantPermRelationService extends IService<UcTenantPermRelation> {

    boolean ternantPerm(TenantPermVO tenantPermVO);

    List<Long> getTenantAllPerm();

    List<Long> getTenantAllPermByTenantId(Long tenantId);
}

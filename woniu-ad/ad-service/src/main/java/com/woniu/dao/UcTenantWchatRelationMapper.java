package com.woniu.dao;

import com.woniu.entity.UcTenantWchatRelation;
import com.esmartwave.niumeng.diap.dao.SuperMapper;

/**
 * <p>
 * 租户微信权表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-04-11
 */
public interface UcTenantWchatRelationMapper extends SuperMapper<UcTenantWchatRelation> {
    public Integer deleteByIdWithFill(UcTenantWchatRelation entity);
}

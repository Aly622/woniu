package com.woniu.dao;

import com.woniu.entity.UcTenantSendEmail;
import com.esmartwave.niumeng.diap.dao.SuperMapper;

/**
 * <p>
 * 租户表发送邮件邮箱配置表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-04-19
 */
public interface UcTenantSendEmailMapper extends SuperMapper<UcTenantSendEmail> {
    public Integer deleteByIdWithFill(UcTenantSendEmail entity);
}

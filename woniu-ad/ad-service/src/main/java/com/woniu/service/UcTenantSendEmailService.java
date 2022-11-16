package com.woniu.service;

import com.woniu.entity.UcTenantSendEmail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.TenantSendEmailVO;

import java.util.List;

/**
 * <p>
 * 租户表发送邮件邮箱配置表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
public interface UcTenantSendEmailService extends IService<UcTenantSendEmail> {

    boolean saveTenantSendEmail(TenantSendEmailVO tenantSendEmailVO);

    List<UcTenantSendEmail> getAllTenantEmail();

    List<UcTenantSendEmail> getEmailByTenantId(Long tenantId);
}

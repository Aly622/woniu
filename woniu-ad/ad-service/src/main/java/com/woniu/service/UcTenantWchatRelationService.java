package com.woniu.service;

import com.woniu.entity.UcTenantWchatRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.AuthorizaWechatVO;

import java.util.List;

/**
 * <p>
 * 租户微信权表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
public interface UcTenantWchatRelationService extends IService<UcTenantWchatRelation> {

    boolean authorizaWechat(AuthorizaWechatVO authorizaWechatVO);

    List<UcTenantWchatRelation> getAuthorizaWechatByTenantId(Long tenantId);
}

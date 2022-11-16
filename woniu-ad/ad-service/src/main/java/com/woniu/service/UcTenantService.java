package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.TenantInfoDTO;
import com.woniu.entity.UcTenant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.TenantPageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcTenantService extends IService<UcTenant> {

    Page<UcTenant> tenantPage(TenantPageVO tenantPageVO);

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.TenantInfoDTO>
     * @desc: 获取所有租户
     * @date: 2022/3/3 15:53
     */
    List<TenantInfoDTO> selectAllTenant();

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.TenantInfoDTO>
     * @desc: 获取所有租户端的租户
     * @date: 2022/4/26 10:59
     */
    List<TenantInfoDTO> getTenantSideTenant();
}

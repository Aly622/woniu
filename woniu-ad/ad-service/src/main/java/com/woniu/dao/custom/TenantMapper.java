package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.dto.TenantInfoDTO;
import com.woniu.entity.UcTenant;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface TenantMapper extends SuperMapper<UcTenant> {

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

    /**
     * @param userId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.TenantInfoDTO>
     * @desc: 根据用户ID获取所有的可用租户
     * @date: 2022/4/25 11:29
     */
    @InterceptorIgnore(tenantLine = "true")
    List<TenantInfoDTO> selectAbleTenantInfoListByUserId(@Param("userId") Long userId);

    /**
     * @param userId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.TenantInfoDTO>
     * @desc: 根据用户ID获取
     * @date: 2022/3/9 16:29
     */
    @InterceptorIgnore(tenantLine = "true")
    List<TenantInfoDTO> selectTenantInfoListByUserId(@Param("userId") Long userId);
}

package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.woniu.dao.SuperMapper;
import com.woniu.dto.PermissionDetailDTO;
import com.woniu.entity.UcTenantPermRelation;
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
public interface TenantPermRelationMapper extends SuperMapper<UcTenantPermRelation> {

    /**
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.PermissionDetailDTO>
     * @desc: 获取租户的权限列表
     * @date: 2022/3/2 14:35
     */
    @InterceptorIgnore(tenantLine = "true")
    List<PermissionDetailDTO> selectPermissionIdList(@Param("tenantId") Long tenantId);

    /**
     * 获取租户下的所有权限id
     * @return
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Long> getTenantAllPermByTenantId(@Param("tenantId")Long tenantId);


    List<Long> getTenantAllPerm();


    @InterceptorIgnore(tenantLine = "true")
    boolean removeByTenantId(Long tenantId);

}

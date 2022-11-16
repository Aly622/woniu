package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.entity.UcRolePermRelation;
import feign.Param;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface RolePermRelationMapper extends SuperMapper<UcRolePermRelation> {

    /**
     * @param roleId
     * @author: mike.ma
     * @return: java.util.List<java.lang.Long>
     * @desc: 根据角色ID查询权限ID列表
     * @date: 2022/3/2 13:26
     */
    List<Long> selectPermissionIdListByRoleId(@Param("roleId") Long roleId);

    @InterceptorIgnore(tenantLine = "true")
    boolean delByRoleId(@Param("roleId")Long roleId, @Param("tenantId") Long tenantId);
}

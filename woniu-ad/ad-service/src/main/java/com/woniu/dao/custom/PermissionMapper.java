package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.entity.UcPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface PermissionMapper extends SuperMapper<UcPermission> {

    /**
     * @param userId
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.entity.UcPermission>
     * @desc: 根据账户ID和租户ID查询权限列表
     * @date: 2022/3/9 17:38
     */
    @InterceptorIgnore(tenantLine = "true")
    List<UcPermission> selectPermissionListByUserIdAndTenantId(@Param("userId") Long userId, @Param("tenantId") Long tenantId);

    @InterceptorIgnore(tenantLine = "true")
    List<UcPermission> getAllPerm();

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.entity.UcPermission>
     * @desc: 获取所有管理端菜单
     * @date: 2022/4/2 14:55
     */
    @InterceptorIgnore(tenantLine = "true")
    List<UcPermission> getAllManagePermissionTree();

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.entity.UcPermission>
     * @desc: 获取所有租户端菜单
     * @date: 2022/4/2 14:55
     */
    @InterceptorIgnore(tenantLine = "true")
    List<UcPermission> getAllTenantPermissionTree();

    @InterceptorIgnore(tenantLine = "true")
    List<UcPermission> allowVendList();
}

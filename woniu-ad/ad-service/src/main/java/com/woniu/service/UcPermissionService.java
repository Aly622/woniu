package com.woniu.service;

import com.woniu.dto.PermissionTreeForLoginDTO;
import com.woniu.entity.UcPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcPermissionService extends IService<UcPermission> {

    /**
     * @param userId
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.PermissionTreeForLoginDTO>
     * @desc: 根据账户ID和租户ID查询权限树
     * @date: 2022/3/9 17:41
     */
    List<PermissionTreeForLoginDTO> selectPermissionListByUserIdAndTenantId(Long userId, Long tenantId);

    List<PermissionTreeForLoginDTO> getAllPermTree();

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.PermissionTreeForLoginDTO>
     * @desc: 获取所有管理端权限树
     * @date: 2022/4/2 14:49
     */
    List<PermissionTreeForLoginDTO> getAllManagePermissionTree();

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.PermissionTreeForLoginDTO>
     * @desc: 获取所有租户端权限树
     * @date: 2022/4/2 14:49
     */
    List<PermissionTreeForLoginDTO> getAllTenantPermissionTree();

    List<UcPermission> allowVendList();
}

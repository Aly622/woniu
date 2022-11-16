package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmartwave.niumeng.diap.dto.*;
import com.woniu.dto.*;
import com.woniu.entity.UcRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmartwave.niumeng.diap.vo.*;
import com.woniu.vo.*;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcRoleService extends IService<UcRole> {

    /**
     * @param rolePageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.RolePageForTenantResultDTO>
     * @desc: 分页查询角色列表（租户端）
     * @date: 2022/2/28 17:52
     */
    Page<RolePageForTenantResultDTO> selectRolePageForTenant(RolePageQueryVO rolePageQuery);

    /**
     * @param rolePageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.RolePageForManageResultDTO>
     * @desc: 分页查询角色列表（管理端）
     * @date: 2022/3/1 10:09
     */
    Page<RolePageForManageResultDTO> selectRolePageForManage(RolePageQueryVO rolePageQuery);



    /**
     * @param createRole
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建角色（租户端和管理端公用）
     * @date: 2022/3/1 10:20
     */
    Boolean createRole(CreateRoleVO createRole);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.entity.UcRole
     * @desc: 获取角色详情（租户端和管理端公用）
     * @date: 2022/3/1 10:50
     */
    UcRole getUcRoleDetail(Long id);

    /**
     * @param updateRole
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 更新角色（租户端和管理端公用）
     * @date: 2022/3/1 11:08
     */
    Boolean updateRole(UpdateRoleVO updateRole);

    /**
     * @param id
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 删除角色（租户端和管理端公用）
     * @date: 2022/3/1 11:37
     */
    Boolean deleteRole(Long id);

    /**
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.PermissionTreeDTO>
     * @desc: 获取权限树列表（租户端和管理端公用）
     * @date: 2022/3/2 14:35
     */
    List<PermissionTreeDTO> getPermissionTreeList(Long tenantId);

    /**
     * @param createOrUpdateRolePermissionRelation
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建或更新角色与权限绑定（租户端和管理端公用）
     * @date: 2022/3/2 11:16
     */
    Boolean createRolePermissionRelation(CreateOrUpdateRolePermissionRelationVO createOrUpdateRolePermissionRelation);

    /**
     * @param roleId
     * @author: mike.ma
     * @return: java.util.List<java.lang.Long>
     * @desc: 根据角色ID获取权限ID列表（租户端和管理端公用）
     * @date: 2022/3/2 11:45
     */
    List<Long> getPermissionIdListByRoleId(Long roleId);

    /**
     * @param roleId
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.BindRoleUserDetailDTO>
     * @desc: 根据角色ID查询绑定的用户列表（租户端）
     * @date: 2022/3/2 14:53
     */
    List<BindRoleUserDetailDTO> selectUserDetailList(Long roleId, Long tenantId);

    /**
     * @param roleQuery
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 获取所有角色（租户端和管理端公用）
     * @date: 2022/3/4 14:23
     */
    List<RoleInfoDTO> selectAllRole(RoleQueryVO roleQuery);

}

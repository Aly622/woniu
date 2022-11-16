package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.esmartwave.niumeng.diap.dto.*;
import com.woniu.dto.*;
import com.woniu.entity.UcRole;
import com.woniu.vo.RolePageQueryVO;
import com.woniu.vo.RoleQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface RoleMapper extends SuperMapper<UcRole> {

    /**
     * @param rolePageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.RolePageForTenantResultDTO>
     * @desc: 分页查询角色列表（租户端）
     * @date: 2022/2/28 17:49
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
     * @param roleId
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.BindRoleUserDetailDTO>
     * @desc: 根据角色ID查询绑定的用户列表
     * @date: 2022/3/2 14:52
     */
    @InterceptorIgnore(tenantLine = "true")
    List<BindRoleUserDetailDTO> selectUserDetailList(@Param("roleId") Long roleId, @Param("tenantId") Long tenantId);

    /**
     * @param roleQuery
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 获取所有角色（租户端和管理端公用）
     * @date: 2022/3/4 14:22
     */
    List<RoleInfoDTO> selectAllRole(RoleQueryVO roleQuery);

    /**
     * @param roleQuery
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 获取所有已启用的角色（租户端和管理端公用）
     * @date: 2022/4/12 09:35
     */
    List<RoleInfoDTO> selectAllAbleRole(RoleQueryVO roleQuery);

    /**
     * @param tenantIdList
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.BindUserRoleDTO>
     * @desc: 根据租户ID列表获取租户和admin角色ID的对应关系
     * @date: 2022/3/4 10:15
     */
    @InterceptorIgnore(tenantLine = "true")
    List<BindUserRoleDTO> selectAdminRoleIdByTenantId(List<Long> tenantIdList);

    @InterceptorIgnore(tenantLine = "true")
    UcRole getAdminRole(Long tenantId);

    /**
     * @param name
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据角色名称查询角色数量
     * @date: 2022/4/1 10:18
     */
    Integer selectRoleCountByName(@Param("name") String name);

    /**
     * @param id
     * @param name
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据角色ID和角色名称查询角色数量
     * @date: 2022/4/1 10:20
     */
    Integer selectRoleCountByIdAndName(@Param("id") Long id, @Param("name") String name);

    /**
     * @param roleIdList
     * @param roleName
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 获取未授权的角色列表
     * @date: 2022/4/11 18:55
     */
    List<RoleInfoDTO> selectNotAuthRoleList(@Param("roleIdList") List<Long> roleIdList, @Param("roleName") String roleName);
}

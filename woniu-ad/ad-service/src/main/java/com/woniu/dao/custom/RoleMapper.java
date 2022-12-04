package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dao.SuperMapper;
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

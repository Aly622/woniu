package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dao.custom.RoleMapper;
import com.woniu.dao.custom.RolePermRelationMapper;
import com.woniu.dao.custom.TenantPermRelationMapper;
import com.woniu.dto.*;
import com.woniu.entity.UcRole;
import com.woniu.dao.UcRoleMapper;
import com.woniu.entity.UcRolePermRelation;
import com.woniu.enums.IsAdminEnum;
import com.woniu.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcRolePermRelationService;
import com.woniu.service.UcRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.service.UcUserRoleRelationService;
import com.woniu.utils.ObjectCopier;
import com.woniu.vo.*;
import groovy.util.logging.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcRoleServiceImpl extends ServiceImpl<UcRoleMapper, UcRole> implements UcRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UcUserRoleRelationService ucUserRoleRelationService;

    @Autowired
    private TenantPermRelationMapper tenantPermRelationMapper;

    @Autowired
    private UcRolePermRelationService ucRolePermRelationService;

    @Autowired
    private RolePermRelationMapper rolePermRelationMapper;

    @Override
    public Page<RolePageForTenantResultDTO> selectRolePageForTenant(RolePageQueryVO rolePageQuery) {
        return roleMapper.selectRolePageForTenant(rolePageQuery);
    }

    @Override
    public Page<RolePageForManageResultDTO> selectRolePageForManage(RolePageQueryVO rolePageQuery) {
        return roleMapper.selectRolePageForManage(rolePageQuery);
    }

    @Override
    public Boolean createRole(CreateRoleVO createRole) {
        Integer roleCount = roleMapper.selectRoleCountByName(createRole.getName());
        if(roleCount > 0) {
            throw new ServiceException(UCResponseCode.ROLE_NAME_IS_EXIST);
        }
        UcRole ucRole = ObjectCopier.copyObject(createRole, UcRole.class);
        ucRole.setIsAdmin(IsAdminEnum.IS_ADMIN_NO.getCode());
        save(ucRole);
        return true;
    }

    @Override
    public UcRole getUcRoleDetail(Long id) {
        UcRole ucRole = getById(id);
        if(ucRole == null) {
            throw new ServiceException(UCResponseCode.UC_ROLE_IS_NOT_EXIST);
        }
        return ucRole;
    }

    @Override
    public Boolean updateRole(UpdateRoleVO updateRole) {
        Integer roleCount = roleMapper.selectRoleCountByIdAndName(updateRole.getId(), updateRole.getName());
        if(roleCount > 0) {
            throw new ServiceException(UCResponseCode.ROLE_NAME_IS_EXIST);
        }
        UcRole ucRole = getById(updateRole.getId());
        if(ucRole == null) {
            throw new ServiceException(UCResponseCode.UC_ROLE_IS_NOT_EXIST);
        }
        ucRole = ObjectCopier.copyObject(updateRole, UcRole.class);
        updateById(ucRole);
        return true;
    }

    @Transactional
    @Override
    public Boolean deleteRole(Long id) {
        Integer userCount = ucUserRoleRelationService.getUserCountByRoleId(id);
        if(userCount > 0) {
            throw new ServiceException(UCResponseCode.CURRENT_ROLE_HAS_USER);
        }
        QueryWrapper<UcRolePermRelation> ucRolePermRelationQueryWrapper = new QueryWrapper<>();
        ucRolePermRelationQueryWrapper.eq("role_id", id);
        ucRolePermRelationService.remove(ucRolePermRelationQueryWrapper);
        removeById(id);
        return true;
    }

    @Override
    public List<PermissionTreeDTO> getPermissionTreeList(Long tenantId) {
        List<PermissionTreeDTO> permissionTreeList = new ArrayList<>();
        List<PermissionDetailDTO> permissionDetailList = tenantPermRelationMapper.selectPermissionIdList(tenantId);
        if(CollectionUtils.isNotEmpty(permissionDetailList)) {
            for(PermissionDetailDTO permissionDetail : permissionDetailList) {
                if(permissionDetail.getParentId().equals(0L)) {
                    permissionTreeList.add(new PermissionTreeDTO(permissionDetail.getId(), permissionDetail.getName(), permissionDetail.getType(), permissionDetail.getParentId()));
                }
            }
        }

        for(PermissionTreeDTO permissionTree : permissionTreeList) {
            List<PermissionTreeDTO> permissionTreeResultList = getOrgTree(permissionTree.getId(), permissionDetailList);
            permissionTree.setChild(permissionTreeResultList);
        }

        return permissionTreeList;
    }

    @Override
    public Boolean createRolePermissionRelation(CreateOrUpdateRolePermissionRelationVO createOrUpdateRolePermissionRelation) {
        List<UcRolePermRelation> ucRolePermRelationList = new ArrayList<>();
        Long roleId = createOrUpdateRolePermissionRelation.getRoleId();

        QueryWrapper<UcRolePermRelation> ucRolePermRelationQueryWrapper = new QueryWrapper<>();
        ucRolePermRelationQueryWrapper.eq("role_id", roleId);
        ucRolePermRelationService.remove(ucRolePermRelationQueryWrapper);

        if(CollectionUtils.isNotEmpty(createOrUpdateRolePermissionRelation.getPermissionIdList())) {
            for(Long permissionId : createOrUpdateRolePermissionRelation.getPermissionIdList()) {
                UcRolePermRelation ucRolePermRelation = new UcRolePermRelation();
                ucRolePermRelation.setRoleId(roleId);
                ucRolePermRelation.setPermissionId(permissionId);
                ucRolePermRelationList.add(ucRolePermRelation);
            }
        }
        ucRolePermRelationService.saveBatch(ucRolePermRelationList);
        return true;
    }

    @Override
    public List<Long> getPermissionIdListByRoleId(Long roleId) {
        return rolePermRelationMapper.selectPermissionIdListByRoleId(roleId);
    }


    @Override
    public List<RoleInfoDTO> selectAllRole(RoleQueryVO roleQuery) {
        return roleMapper.selectAllRole(roleQuery);
    }

    /**
     * @param permissionId
     * @param permissionDetailList
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO>
     * @desc: 递归获取权限树列表
     * @date: 2022/2/25 15:39
     */
    private List<PermissionTreeDTO> getOrgTree(Long permissionId, List<PermissionDetailDTO> permissionDetailList) {
        List<PermissionTreeDTO> childPermissionList = new ArrayList<>();
        for (PermissionDetailDTO permissionDetail : permissionDetailList) {
            if (permissionId.equals(permissionDetail.getParentId())) {
                childPermissionList.add(new PermissionTreeDTO(permissionDetail.getId(), permissionDetail.getName(), permissionDetail.getType(), permissionDetail.getParentId()));
            }
        }
        for (PermissionTreeDTO permissionTree : childPermissionList) {
            permissionTree.setChild(getOrgTree(permissionTree.getId(), permissionDetailList));
        }

        if (CollectionUtils.isEmpty(childPermissionList)) {
            return null;
        }
        return childPermissionList;
    }

}

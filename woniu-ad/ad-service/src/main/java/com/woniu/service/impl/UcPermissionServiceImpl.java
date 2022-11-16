package com.woniu.service.impl;

import com.woniu.dao.custom.PermissionMapper;
import com.woniu.dto.PermissionTreeForLoginDTO;
import com.woniu.entity.UcPermission;
import com.woniu.dao.UcPermissionMapper;
import com.woniu.service.UcPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcPermissionServiceImpl extends ServiceImpl<UcPermissionMapper, UcPermission> implements UcPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionTreeForLoginDTO> selectPermissionListByUserIdAndTenantId(Long userId, Long tenantId) {
        List<PermissionTreeForLoginDTO> permissionTreeList = new ArrayList<>();
        List<UcPermission> ucPermissionList = permissionMapper.selectPermissionListByUserIdAndTenantId(userId, tenantId);

        if(CollectionUtils.isNotEmpty(ucPermissionList)) {
            for(UcPermission ucPermission : ucPermissionList) {
                if(ucPermission.getParentId().equals(0L)) {
                    permissionTreeList.add(new PermissionTreeForLoginDTO(ucPermission.getId(), ucPermission.getName(), ucPermission.getCode(), ucPermission.getType(), ucPermission.getParentId(), ucPermission.getUrl(), ucPermission.getDisplayOrder(), ucPermission.getIcon()));
                }
            }
        }

        for(PermissionTreeForLoginDTO permissionTree : permissionTreeList) {
            List<PermissionTreeForLoginDTO> permissionTreeResultList = getOrgTree(permissionTree.getId(), ucPermissionList);
            permissionTree.setChildPermissionList(permissionTreeResultList);
        }

        return permissionTreeList;
    }

    @Override
    public List<PermissionTreeForLoginDTO> getAllPermTree() {
        List<PermissionTreeForLoginDTO> permissionTreeList = new ArrayList<>();
        List<UcPermission> permissions =  permissionMapper.getAllPerm();

        if(CollectionUtils.isNotEmpty(permissions)) {
            for(UcPermission ucPermission : permissions) {
                if(ucPermission.getParentId().equals(0L)) {
                    permissionTreeList.add(new PermissionTreeForLoginDTO(ucPermission.getId(), ucPermission.getName(), ucPermission.getCode(), ucPermission.getType(), ucPermission.getParentId(), ucPermission.getUrl(), ucPermission.getDisplayOrder(), ucPermission.getIcon()));
                }
            }
        }
        for(PermissionTreeForLoginDTO permissionTree : permissionTreeList) {
            List<PermissionTreeForLoginDTO> permissionTreeResultList = getOrgTree(permissionTree.getId(), permissions);
            permissionTree.setChildPermissionList(permissionTreeResultList);
        }

        return permissionTreeList;
    }

    @Override
    public List<PermissionTreeForLoginDTO> getAllManagePermissionTree() {
        List<PermissionTreeForLoginDTO> permissionTreeList = new ArrayList<>();
        List<UcPermission> permissions =  permissionMapper.getAllManagePermissionTree();

        if(CollectionUtils.isNotEmpty(permissions)) {
            for(UcPermission ucPermission : permissions) {
                if(ucPermission.getParentId().equals(0L)) {
                    permissionTreeList.add(new PermissionTreeForLoginDTO(ucPermission.getId(), ucPermission.getName(), ucPermission.getCode(), ucPermission.getType(), ucPermission.getParentId(), ucPermission.getUrl(), ucPermission.getDisplayOrder(), ucPermission.getIcon()));
                }
            }
        }
        for(PermissionTreeForLoginDTO permissionTree : permissionTreeList) {
            List<PermissionTreeForLoginDTO> permissionTreeResultList = getOrgTree(permissionTree.getId(), permissions);
            permissionTree.setChildPermissionList(permissionTreeResultList);
        }

        return permissionTreeList;
    }

    @Override
    public List<PermissionTreeForLoginDTO> getAllTenantPermissionTree() {
        List<PermissionTreeForLoginDTO> permissionTreeList = new ArrayList<>();
        List<UcPermission> permissions =  permissionMapper.getAllTenantPermissionTree();

        if(CollectionUtils.isNotEmpty(permissions)) {
            for(UcPermission ucPermission : permissions) {
                if(ucPermission.getParentId().equals(0L)) {
                    permissionTreeList.add(new PermissionTreeForLoginDTO(ucPermission.getId(), ucPermission.getName(), ucPermission.getCode(), ucPermission.getType(), ucPermission.getParentId(), ucPermission.getUrl(), ucPermission.getDisplayOrder(), ucPermission.getIcon()));
                }
            }
        }
        for(PermissionTreeForLoginDTO permissionTree : permissionTreeList) {
            List<PermissionTreeForLoginDTO> permissionTreeResultList = getOrgTree(permissionTree.getId(), permissions);
            permissionTree.setChildPermissionList(permissionTreeResultList);
        }

        return permissionTreeList;
    }

    @Override
    public List<UcPermission> allowVendList() {
        return permissionMapper.allowVendList();
    }

    /**
     * @param permissionId
     * @param ucPermissionList
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO>
     * @desc: 递归获取权限树列表
     * @date: 2022/2/25 15:39
     */
    private List<PermissionTreeForLoginDTO> getOrgTree(Long permissionId, List<UcPermission> ucPermissionList) {
        List<PermissionTreeForLoginDTO> childPermissionList = new ArrayList<>();
        for (UcPermission ucPermission : ucPermissionList) {
            if (permissionId.equals(ucPermission.getParentId())) {
                childPermissionList.add(new PermissionTreeForLoginDTO(ucPermission.getId(), ucPermission.getName(), ucPermission.getCode(), ucPermission.getType(), ucPermission.getParentId(), ucPermission.getUrl(), ucPermission.getDisplayOrder(), ucPermission.getIcon()));
            }
        }
        for (PermissionTreeForLoginDTO permissionTree : childPermissionList) {
            permissionTree.setChildPermissionList(getOrgTree(permissionTree.getId(), ucPermissionList));
        }

        if (CollectionUtils.isEmpty(childPermissionList)) {
            return null;
        }
        return childPermissionList;
    }
}

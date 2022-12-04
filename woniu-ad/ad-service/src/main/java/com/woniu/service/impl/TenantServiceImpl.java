package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.woniu.entity.*;
import com.woniu.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.woniu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TenantServiceImpl implements TenantService {


    @Autowired
    private UcTenantService tenantService;

    @Autowired
    private UcRoleService roleService;

    @Autowired
    private UcOrgService orgService;

    @Autowired
    private UcPermissionService permissionService;

    @Autowired
    private UcRolePermRelationService rolePermRelationService;


    /**
     * 新建租户
     * 新建租户需初始化信息
     * 1）集团总部及生产商组织的根节点
     * 2）管理员角色，含有租户下所有页面菜单权限（其他角色待后续确认）
     * @param ucTenant
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean saveTenant(UcTenant ucTenant) throws Exception{
        QueryWrapper<UcTenant> qy = new QueryWrapper<UcTenant>()
                .eq("name", ucTenant.getName());
        int count = tenantService.count(qy);
        if (count>0) throw new ServiceException(UCResponseCode.NAME_IS_EXIST);
        boolean isSuccess = tenantService.save(ucTenant);
        if (isSuccess) initTenantInfo(ucTenant.getId());
        return isSuccess;
    }


    /**
     * 编辑租户
     * @param ucTenant
     * @return
     */
    @Override
    public boolean updateTenant(UcTenant ucTenant) {

        QueryWrapper<UcTenant> qy = new QueryWrapper<UcTenant>()
                .eq("name", ucTenant.getName())
                .ne("id",ucTenant.getId());
        int count = tenantService.count(qy);
        if (count>0) throw new ServiceException(UCResponseCode.NAME_IS_EXIST);
        boolean isSuccess = tenantService.updateById(ucTenant);
        if (!isSuccess) throw new ServiceException(UCResponseCode.UPDATE_TENANT_ERROR);
        return isSuccess;
    }

    /**
     * 初始化租户信息
     * @param tenantId
     */

    private void initTenantInfo(Long tenantId) throws Exception{
        //1:创建管理员角色
        UcRole ucRole = new UcRole();
        ucRole.setName("admin");
        ucRole.setScope(3);
        ucRole.setStatus(1);
        ucRole.setIsAdmin(1);
        ucRole.setTenantId(tenantId);
        boolean saveRole = roleService.save(ucRole);
        if (!saveRole)throw new ServiceException(UCResponseCode.CREATE_ROLE_ERROR);

        //创建默认生产商管理员角色
        UcRole vendRole = new UcRole();
        vendRole.setName("vendAdmin");
        vendRole.setScope(3);
        vendRole.setStatus(1);
        vendRole.setIsAdmin(0);
        vendRole.setIsVendAdmin(1);
        vendRole.setTenantId(tenantId);
        boolean saveVendRole = roleService.save(vendRole);
        if (!saveVendRole)throw new ServiceException(UCResponseCode.CREATE_ROLE_ERROR);

        //生产商角色绑定默认权限
        //获取生产商默认权限
        List<UcPermission> permissions = permissionService.allowVendList();
        List<UcRolePermRelation> ucRolePermRelations = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(permissions)) {
            permissions.stream().map(UcPermission::getId).collect(Collectors.toList()).forEach(p ->{
                UcRolePermRelation rolePermRelation = new UcRolePermRelation();
                rolePermRelation.setRoleId(vendRole.getId());
                rolePermRelation.setPermissionId(p);
                rolePermRelation.setTenantId(tenantId);
                ucRolePermRelations.add(rolePermRelation);
            });
        }
        if (CollectionUtils.isNotEmpty(ucRolePermRelations)) rolePermRelationService.saveBatch(ucRolePermRelations);



        //2:创建集团总部根节点
        UcOrg ucOrg = new UcOrg();
        ucOrg.setName("集团总部");
        ucOrg.setParentId(0L);
        ucOrg.setStatus(1);
        ucOrg.setTenantId(tenantId);
        boolean saveOrg = orgService.save(ucOrg);
        if (!saveOrg)throw new ServiceException(UCResponseCode.CREATE_ORG_ERROR);
    }
}

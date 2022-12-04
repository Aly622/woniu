package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.woniu.dao.custom.RoleMapper;
import com.woniu.dao.custom.RolePermRelationMapper;
import com.woniu.dao.custom.TenantPermRelationMapper;
import com.woniu.entity.UcRole;
import com.woniu.entity.UcRolePermRelation;
import com.woniu.entity.UcTenantPermRelation;
import com.woniu.dao.UcTenantPermRelationMapper;
import com.woniu.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcRolePermRelationService;
import com.woniu.service.UcTenantPermRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.TenantPermVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Service
public class UcTenantPermRelationServiceImpl extends ServiceImpl<UcTenantPermRelationMapper, UcTenantPermRelation> implements UcTenantPermRelationService {

    @Autowired
    private TenantPermRelationMapper mapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UcRolePermRelationService rolePermRelationService;

    @Autowired
    private RolePermRelationMapper rolePermRelationMapper;
    /**
     * 租户权限绑定
     * 绑定租户权限时,先删除在插入
     * @param tenantPermVO
     * @return
     */
    @Override
    @Transactional
    public boolean ternantPerm(TenantPermVO tenantPermVO) {
        if (tenantPermVO.getTenantId() == null) throw new ServiceException(UCResponseCode.PARAM_IS_NOT_NULL);
        //删除中间表中关联租户权限
        boolean remove = mapper.removeByTenantId(tenantPermVO.getTenantId());
        if (CollectionUtils.isNotEmpty(tenantPermVO.getPermIds())) {
            List<UcTenantPermRelation> ucTenantPermRelations = new ArrayList<>();
            for (Long permId : tenantPermVO.getPermIds()) {
                UcTenantPermRelation ucTenantPermRelation = new UcTenantPermRelation();
                ucTenantPermRelation.setTenantId(tenantPermVO.getTenantId());
                ucTenantPermRelation.setPermissionId(permId);
                ucTenantPermRelations.add(ucTenantPermRelation);
            }
            boolean isSuccess = saveBatch(ucTenantPermRelations);

            //租户管理员角色绑定租户下所有权限
            bindAdminRole(tenantPermVO);
            return isSuccess;
        }
        return remove;
    }

    private void bindAdminRole(TenantPermVO tenantPermVO) {
        //获取租户下管理员角色
        UcRole adminRole = roleMapper.getAdminRole(tenantPermVO.getTenantId());
        //删除原本绑定的角色权限
        boolean delByRole =  rolePermRelationMapper.delByRoleId(adminRole.getId(),tenantPermVO.getTenantId());

        List<UcRolePermRelation> rolePermRelations = new ArrayList<>();
        for (Long permId : tenantPermVO.getPermIds()) {
            UcRolePermRelation rolePermRelation = new UcRolePermRelation();
            rolePermRelation.setPermissionId(permId);
            rolePermRelation.setTenantId(tenantPermVO.getTenantId());
            rolePermRelation.setRoleId(adminRole.getId());
            rolePermRelations.add(rolePermRelation);
        }
        rolePermRelationService.saveBatch(rolePermRelations);
    }

    @Override
    public List<Long> getTenantAllPermByTenantId(Long tenantId) {
        List<Long> res =  mapper.getTenantAllPermByTenantId(tenantId);
        return res;
    }

    @Override
    public List<Long> getTenantAllPerm() {
        List<Long> res =  mapper.getTenantAllPerm();
        return res;
    }
}

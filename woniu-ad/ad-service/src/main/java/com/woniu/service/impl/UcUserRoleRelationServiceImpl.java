package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.UcUserRoleRelationMapper;
import com.woniu.entity.UcUserRoleRelation;
import com.woniu.service.UcUserRoleRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcUserRoleRelationServiceImpl extends ServiceImpl<UcUserRoleRelationMapper, UcUserRoleRelation> implements UcUserRoleRelationService {

    @Override
    public Integer getUserCountByRoleId(Long roleId) {
        QueryWrapper<UcUserRoleRelation> ucUserRoleRelationQueryWrapper = new QueryWrapper<>();
        ucUserRoleRelationQueryWrapper.eq("role_id", roleId);
        return count(ucUserRoleRelationQueryWrapper);
    }
}

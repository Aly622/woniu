package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniu.entity.UcUserOrgRelation;
import com.woniu.dao.UcUserOrgRelationMapper;
import com.woniu.service.UcUserOrgRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与组织架构关系表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcUserOrgRelationServiceImpl extends ServiceImpl<UcUserOrgRelationMapper, UcUserOrgRelation> implements UcUserOrgRelationService {

    @Override
    public Integer getUserCountByOrgId(Long orgId) {
        QueryWrapper<UcUserOrgRelation> ucUserOrgRelationQueryWrapper = new QueryWrapper<>();
        ucUserOrgRelationQueryWrapper.eq("org_id", orgId);
        return count(ucUserOrgRelationQueryWrapper);
    }
}

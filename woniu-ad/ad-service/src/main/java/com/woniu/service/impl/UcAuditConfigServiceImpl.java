package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.dao.UcAuditConfigMapper;
import com.woniu.dao.custom.AuditConfigMapper;
import com.woniu.dto.AuditConfigDTO;
import com.woniu.entity.UcAuditConfig;
import com.woniu.entity.UcAuditConfigDetail;
import com.woniu.service.UcAuditConfigDetailService;
import com.woniu.service.UcAuditConfigService;
import com.esmartwave.niumeng.diap.utils.ObjectCopier;
import com.esmartwave.niumeng.diap.vo.*;
import com.woniu.vo.*;
import groovy.util.logging.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 审批流配置表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcAuditConfigServiceImpl extends ServiceImpl<UcAuditConfigMapper, UcAuditConfig> implements UcAuditConfigService {

    @Autowired
    private AuditConfigMapper auditConfigMapper;

    @Autowired
    private UcAuditConfigDetailService ucAuditConfigDetailService;

    @Override
    public Page<UcAuditConfig> selectAuditConfigPageResult(AuditConfigPageQueryVO auditConfigPageQuery) {
        return auditConfigMapper.selectAuditConfigPageResult(auditConfigPageQuery);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED)
    @Override
    public Boolean createAuditConfig(CreateAuditConfigVO createAuditConfig) {
        UcAuditConfig ucAuditConfig = ObjectCopier.copyObject(createAuditConfig, UcAuditConfig.class);
        save(ucAuditConfig);

        List<UcAuditConfigDetail> ucAuditConfigDetailList = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(createAuditConfig.getCreateAuditConfigRoleList())) {
            for(CreateAuditConfigRoleVO createAuditConfigRole : createAuditConfig.getCreateAuditConfigRoleList()) {
                UcAuditConfigDetail ucAuditConfigDetail = new UcAuditConfigDetail();
                ucAuditConfigDetail.setConfigId(ucAuditConfig.getId());
                ucAuditConfigDetail.setRoleId(createAuditConfigRole.getRoleId());
                ucAuditConfigDetail.setFlowOrder(createAuditConfigRole.getFlowOrder());
                ucAuditConfigDetailList.add(ucAuditConfigDetail);
            }
        }
        if(CollectionUtils.isNotEmpty(ucAuditConfigDetailList)) {
            ucAuditConfigDetailService.saveBatch(ucAuditConfigDetailList);
        }
        return true;
    }

    @Override
    public AuditConfigDTO selectAuditConfigDetail(Long id) {
        return auditConfigMapper.selectAuditConfigDetail(id);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED)
    @Override
    public Boolean updateAuditConfig(UpdateAuditConfigVO updateAuditConfig) {
        UcAuditConfig ucAuditConfig = ObjectCopier.copyObject(updateAuditConfig, UcAuditConfig.class);
        updateById(ucAuditConfig);

        QueryWrapper<UcAuditConfigDetail> ucAuditConfigDetailQueryWrapper = new QueryWrapper<>();
        ucAuditConfigDetailQueryWrapper.eq("config_id", updateAuditConfig.getId());
        ucAuditConfigDetailService.remove(ucAuditConfigDetailQueryWrapper);

        List<UcAuditConfigDetail> ucAuditConfigDetailList = new ArrayList<>();
        for(UpdateAuditConfigRoleVO updateAuditConfigRole : updateAuditConfig.getAuditConfigDetailList()) {
            UcAuditConfigDetail ucAuditConfigDetail = new UcAuditConfigDetail();
            ucAuditConfigDetail.setConfigId(ucAuditConfig.getId());
            ucAuditConfigDetail.setRoleId(updateAuditConfigRole.getRoleId());
            ucAuditConfigDetail.setFlowOrder(updateAuditConfigRole.getFlowOrder());
            ucAuditConfigDetailList.add(ucAuditConfigDetail);
        }
        if(CollectionUtils.isNotEmpty(ucAuditConfigDetailList)) {
            ucAuditConfigDetailService.saveBatch(ucAuditConfigDetailList);
        }
        return true;
    }
}

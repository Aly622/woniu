package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dao.custom.TenantMapper;
import com.woniu.dto.TenantInfoDTO;
import com.woniu.entity.UcTenant;
import com.woniu.dao.UcTenantMapper;
import com.woniu.service.UcTenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.vo.TenantPageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UcTenantServiceImpl extends ServiceImpl<UcTenantMapper, UcTenant> implements UcTenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public Page<UcTenant> tenantPage(TenantPageVO tenantPageVO) {
        QueryWrapper<UcTenant> qry = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(tenantPageVO.getName())) qry.like("name",tenantPageVO.getName());
        qry.orderByDesc("created_at");
        Page<UcTenant> page = baseMapper.selectPage(tenantPageVO, qry);
        return page;


    }

    @Override
    public List<TenantInfoDTO> selectAllTenant() {
        return tenantMapper.selectAllTenant();
    }

    @Override
    public List<TenantInfoDTO> getTenantSideTenant() {
        return tenantMapper.getTenantSideTenant();
    }
}

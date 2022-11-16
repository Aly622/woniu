package com.woniu.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.woniu.dao.custom.TenantWchatRelationMapper;
import com.woniu.entity.UcTenantWchatRelation;
import com.woniu.dao.UcTenantWchatRelationMapper;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.esmartwave.niumeng.diap.response.BCResponseCode;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcTenantWchatRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.esmartwave.niumeng.diap.utils.ObjectCopier;
import com.woniu.vo.AuthorizaWechatVO;
import com.esmartwave.niumeng.diap.vo.ProvinceCityDistrictVO;
import com.woniu.vo.SaveTenantWchatRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 租户微信权表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
@Service
public class UcTenantWchatRelationServiceImpl extends ServiceImpl<UcTenantWchatRelationMapper, UcTenantWchatRelation> implements UcTenantWchatRelationService {

    @Autowired
    private TenantWchatRelationMapper tenantWchatRelationMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean authorizaWechat(AuthorizaWechatVO authorizaWechatVO) {
        //根据租户id清楚租户下绑定的微信数据
        tenantWchatRelationMapper.delByTenantId(authorizaWechatVO.getTenantId());
        //保存
        if (CollectionUtils.isNotEmpty(authorizaWechatVO.getWechatInfo())) {

            //判断是否有重复数据
            Set<SaveTenantWchatRelationVO> set = new HashSet<>(authorizaWechatVO.getWechatInfo());
            if (authorizaWechatVO.getWechatInfo().size() != set.size()) throw new ServiceException(UCResponseCode.DUPLICATE_OBJECT);

            List<UcTenantWchatRelation> ucTenantWchatRelations = ObjectCopier.copyList(authorizaWechatVO.getWechatInfo(), UcTenantWchatRelation.class);
            ucTenantWchatRelations.stream().forEach(a ->{
                a.setTenantId(authorizaWechatVO.getTenantId());
            });
            boolean isSuccess = saveBatch(ucTenantWchatRelations);
            return isSuccess;
        }
        return true;
    }

    @Override
    public List<UcTenantWchatRelation> getAuthorizaWechatByTenantId(Long tenantId) {
        return tenantWchatRelationMapper.getAuthorizaWechatByTenantId(tenantId);
    }
}

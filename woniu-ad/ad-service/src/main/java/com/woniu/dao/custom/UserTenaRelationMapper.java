package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.dto.UserBindTenantDTO;
import com.woniu.entity.UcUserTenaRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与租户关系表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UserTenaRelationMapper extends SuperMapper<UcUserTenaRelation> {

    /**
     * @param userId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.UserBindTenantDTO>
     * @desc: 根据账户ID查询所有的租户
     * @date: 2022/3/4 15:36
     */
    @InterceptorIgnore(tenantLine = "true")
    List<UserBindTenantDTO> selectAllTenant(@Param("userId") Long userId);

    /**
     * @param userId
     * @param tenantIdList
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID删除所有账户与租户的绑定关系
     * @date: 2022/3/9 11:22
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer deleteUserTenantRelationByUserId(@Param("userId") Long userId, @Param("list") List<Long> tenantIdList);

}

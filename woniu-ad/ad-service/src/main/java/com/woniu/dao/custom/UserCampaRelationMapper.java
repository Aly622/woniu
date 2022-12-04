package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.woniu.dao.SuperMapper;
import com.woniu.entity.UcUserCampaRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与活动关联表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UserCampaRelationMapper extends SuperMapper<UcUserCampaRelation> {

    /**
     * @param userId
     * @param tenantIdList
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和租户ID列表删除账户和活动关系
     * @date: 2022/3/4 16:16
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer deleteUserCampRelationByUserId(@Param("userId") Long userId, @Param("list") List<Long> tenantIdList);
}

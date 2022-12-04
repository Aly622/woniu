package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.woniu.dao.SuperMapper;
import com.woniu.entity.UcUserRoleRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UserRoleRelationMapper extends SuperMapper<UcUserRoleRelation> {

    /**
     * @param userId
     * @param tenantIdList
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和租户ID列表删除账户和角色关系
     * @date: 2022/3/4 16:16
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer deleteUserRoleRelationByUserId(@Param("userId") Long userId, @Param("list") List<Long> tenantIdList);
}

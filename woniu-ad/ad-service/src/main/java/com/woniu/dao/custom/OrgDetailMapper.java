package com.woniu.dao.custom;

import com.woniu.dao.SuperMapper;
import com.woniu.entity.UcOrgDetail;

import java.util.List;

/**
 * <p>
 * 组织架构子表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface OrgDetailMapper extends SuperMapper<UcOrgDetail> {

    /**
     * @param orgId
     * @author: mike.ma
     * @return: java.util.List<java.lang.Long>
     * @desc: 根据组织ID查询子组织ID列表
     * @date: 2022/2/25 15:28
     */
    List<Long> selectChildOrgIdList(Long orgId);
}

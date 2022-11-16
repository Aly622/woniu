package com.woniu.service;

import com.woniu.entity.UcUserOrgRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户与组织架构关系表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcUserOrgRelationService extends IService<UcUserOrgRelation> {

    /**
     * @param orgId
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据组织ID
     * @date: 2022/2/28 11:46
     */
    Integer getUserCountByOrgId(Long orgId);
}

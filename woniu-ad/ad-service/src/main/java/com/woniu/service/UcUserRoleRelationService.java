package com.woniu.service;

import com.woniu.entity.UcUserRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcUserRoleRelationService extends IService<UcUserRoleRelation> {

    /**
     * @param roleId
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据角色ID查询用户数量
     * @date: 2022/3/1 11:33
     */
    Integer getUserCountByRoleId(Long roleId);
}

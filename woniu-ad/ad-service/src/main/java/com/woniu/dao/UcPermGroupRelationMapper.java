package com.woniu.dao;

import com.woniu.entity.UcPermGroupRelation;

/**
 * <p>
 * 菜单组权限关系表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-03-10
 */
public interface UcPermGroupRelationMapper extends SuperMapper<UcPermGroupRelation> {
    public Integer deleteByIdWithFill(UcPermGroupRelation entity);
}

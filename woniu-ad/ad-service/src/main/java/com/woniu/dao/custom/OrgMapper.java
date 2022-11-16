package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.dto.BindUserOrgDTO;
import com.woniu.dto.OrgListPageResultDTO;
import com.woniu.entity.UcOrg;
import com.woniu.vo.OrgListPageQueryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 组织架构表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface OrgMapper extends SuperMapper<UcOrg> {

    /**
     * @param
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.entity.UcOrg>
     * @desc: 为组织树结构获取组织列表
     * @date: 2022/2/25 11:45
     */
    List<UcOrg> selectOrgListForOrgTree();

    /**
     * @param 
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.entity.UcOrg>
     * @desc: 为组织树结构获取启用的组织列表
     * @date: 2022/4/13 14:47
     */
    List<UcOrg> selectAbleOrgListForOrgTree();

    /**
     * @param orgListPageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.OrgListPageResultDTO>
     * @desc: 根据组织名称和父组织ID分页查询组织列表
     * @date: 2022/2/25 11:47
     */
    Page<OrgListPageResultDTO> selectOrgListPageResult(OrgListPageQueryVO orgListPageQuery);

    /**
     * @param tenantIdList
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.BindUserOrgDTO>
     * @desc: 根据租户ID列表获取租户和租户下的根组织ID关系
     * @date: 2022/3/4 11:13
     */
    @InterceptorIgnore(tenantLine = "true")
    List<BindUserOrgDTO> selectRootOrgIdByTenantId(List<Long> tenantIdList);

    /**
     * @param name
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据组织名称查询组织数量
     * @date: 2022/4/1 11:24
     */
    Integer selectOrgCountByName(@Param("name") String name);

    /**
     * @param id
     * @param name
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据组织ID和组织名称查询组织数量
     * @date: 2022/4/1 11:25
     */
    Integer selectOrgCountByIdAndName(@Param("id") Long id, @Param("name") String name);

}

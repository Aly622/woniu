package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.OrgListPageResultDTO;
import com.woniu.dto.OrgTreeResultDTO;
import com.woniu.entity.UcOrg;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.vo.CreateOrgVO;
import com.woniu.vo.OrgListPageQueryVO;
import com.woniu.vo.UpdateOrgVO;

/**
 * <p>
 * 组织架构表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcOrgService extends IService<UcOrg> {

    /**
     * @param
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO
     * @desc: 获取组织结构树
     * @date: 2022/2/25 11:18
     */
    OrgTreeResultDTO getOrgTree();

    /**
     * @param
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.OrgTreeResultDTO
     * @desc: 获取启用的组织结构树
     * @date: 2022/2/25 11:18
     */
    OrgTreeResultDTO getAbleOrgTree();

    /**
     * @param orgListPageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.OrgListPageResultDTO>
     * @desc: 根据组织名称和父组织ID查询组织列表
     * @date: 2022/2/25 11:19
     */
    Page<OrgListPageResultDTO> getOrgListPage(OrgListPageQueryVO orgListPageQuery);

    /**
     * @param createOrg
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建组织架构
     * @date: 2022/2/25 15:39
     */
    Boolean createOrg(CreateOrgVO createOrg);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.entity.UcOrg
     * @desc: 获取组织架构详情
     * @date: 2022/2/28 10:27
     */
    UcOrg getUcOrg(Long id);

    /**
     * @param updateOrg
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 更新组织架构
     * @date: 2022/2/28 10:36
     */
    Boolean updateUcOrg(UpdateOrgVO updateOrg);

    /**
     * @param id
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 删除组织架构
     * @date: 2022/2/28 13:13
     */
    Boolean deleteUcOrg(Long id);
}

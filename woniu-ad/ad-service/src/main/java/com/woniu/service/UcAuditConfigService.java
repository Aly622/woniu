package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.woniu.dto.AuditConfigDTO;
import com.woniu.entity.UcAuditConfig;
import com.woniu.vo.AuditConfigPageQueryVO;
import com.woniu.vo.CreateAuditConfigVO;
import com.woniu.vo.UpdateAuditConfigVO;

/**
 * <p>
 * 审批流配置表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcAuditConfigService extends IService<UcAuditConfig> {

    /**
     * @param auditConfigPageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.entity.UcAuditConfig>
     * @desc: 分页查询审批流配置
     * @date: 2022/3/2 15:54
     */
    Page<UcAuditConfig> selectAuditConfigPageResult(AuditConfigPageQueryVO auditConfigPageQuery);

    /**
     * @param createAuditConfig
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建审批流
     * @date: 2022/3/2 16:57
     */
    Boolean createAuditConfig(CreateAuditConfigVO createAuditConfig);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.AuditConfigDTO
     * @desc: 获取审批流详情
     * @date: 2022/3/2 17:38
     */
    AuditConfigDTO selectAuditConfigDetail(Long id);

    /**
     * @param updateAuditConfig
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 更新审批流
     * @date: 2022/3/2 17:51
     */
    Boolean updateAuditConfig(UpdateAuditConfigVO updateAuditConfig);
}

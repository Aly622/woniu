package com.woniu.dao.custom;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmartwave.niumeng.diap.dao.SuperMapper;
import com.woniu.dto.AuditConfigDTO;
import com.woniu.entity.UcAuditConfig;
import com.woniu.vo.AuditConfigPageQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 审批流配置表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface AuditConfigMapper extends SuperMapper<UcAuditConfig> {

    /**
     * @param auditConfigPageQuery
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.entity.UcAuditConfig>
     * @desc: 分页查询审批流配置
     * @date: 2022/3/2 15:49
     */
    Page<UcAuditConfig> selectAuditConfigPageResult(AuditConfigPageQueryVO auditConfigPageQuery);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.AuditConfigDTO
     * @desc: 获取审批流详情
     * @date: 2022/3/2 17:38
     */
    AuditConfigDTO selectAuditConfigDetail(@Param("id") Long id);
}

package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 审批流详情对象  
 * @date: 2022-03-02 17:26  
 **************************************/
@Data
public class AuditConfigDTO {

    /**
     * 审批流ID
     */
    @ApiModelProperty(value = "审批流ID")
    private Long id;

    /**
     * 审批流名称
     */
    @ApiModelProperty(value = "审批流名称")
    private String name;

    /**
     * 审批流配置角色节点列表
     */
    @ApiModelProperty(value = "审批流配置角色节点列表")
    private List<AuditConfigDetailDTO> auditConfigDetailList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}

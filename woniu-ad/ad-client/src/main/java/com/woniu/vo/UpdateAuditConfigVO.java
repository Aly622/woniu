package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新审批流入参对象  
 * @date: 2022-03-02 17:45  
 **************************************/
@Data
public class UpdateAuditConfigVO {

    /**
     * 审批流ID
     */
    @ApiModelProperty(value = "审批流ID", required = true)
    @NotNull(message="审批流ID不能为空")
    private Long id;

    /**
     * 审批流名称
     */
    @ApiModelProperty(value = "审批流名称", required = true)
    @NotBlank(message="审批流名称不能为空")
    private String name;

    /**
     * 审批流配置角色节点列表
     */
    @ApiModelProperty(value = "审批流配置角色节点列表", required = true)
    @NotEmpty(message="审批流配置角色节点列表不能为空")
    private List<UpdateAuditConfigRoleVO> auditConfigDetailList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "审批流名称")
    private String remark;
}

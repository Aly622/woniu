package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 创建审批流  
 * @date: 2022-03-02 16:31  
 **************************************/
@Data
public class CreateAuditConfigVO {

    /**
     * 审批流名称
     */
    @ApiModelProperty(value = "审批流名称", required = true)
    @NotBlank(message="审批流名称不能为空")
    private String name;

    /**
     * 角色节点列表
     */
    @ApiModelProperty(value = "角色节点列表", required = true)
    @NotEmpty(message="角色节点列表为空")
    private List<CreateAuditConfigRoleVO> createAuditConfigRoleList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}

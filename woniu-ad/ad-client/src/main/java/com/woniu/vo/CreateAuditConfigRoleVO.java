package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 创建审批流角色入参对象  
 * @date: 2022-03-02 16:37  
 **************************************/
@Data
public class CreateAuditConfigRoleVO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    /**
     * 审批流节点排序
     */
    @ApiModelProperty(value = "审批流节点排序", required = true)
    private Integer flowOrder;
}

package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新审批流角色节点对象  
 * @date: 2022-03-02 17:48  
 **************************************/
@Data
public class UpdateAuditConfigRoleVO {

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

package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 审批流详情对象  
 * @date: 2022-03-02 17:27  
 **************************************/
@Data
public class AuditConfigDetailDTO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 审批流节点排序
     */
    @ApiModelProperty(value = "审批流节点排序")
    private Integer flowOrder;
}

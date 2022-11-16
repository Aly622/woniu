package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 组织架构列表数据  
 * @date: 2022-02-25 10:56  
 **************************************/
@Data
public class OrgListPageResultDTO {

    /**
     * 组织架构ID
     */
    @ApiModelProperty(value = "组织架构ID")
    private Long id;

    /**
     * 组织架构名称
     */
    @ApiModelProperty(value = "组织架构名称")
    private String name;

    /**
     * 父组织架构名称
     */
    @ApiModelProperty(value = "父组织架构名称")
    private String parentOrgName;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;
}

package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 组织架构树形结构结果对象  
 * @date: 2022-02-25 10:52  
 **************************************/
@Data
public class OrgTreeResultDTO {

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
     * 父组织架构ID
     */
    @ApiModelProperty(value = "父组织架构ID")
    private Long parentId;

    /**
     * 子组织架构列表
     */
    @ApiModelProperty(value = "子组织架构树形结构列表")
    private List<OrgTreeResultDTO> childOrgList;
}

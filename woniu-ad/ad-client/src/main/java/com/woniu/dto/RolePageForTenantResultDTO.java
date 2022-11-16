package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色分页查询结果对象  
 * @date: 2022-02-28 17:03  
 **************************************/
@Data
public class RolePageForTenantResultDTO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 数据范围（1：仅本人，2：本部门，3：全部数据）
     */
    @ApiModelProperty(value = "数据范围（1：仅本人，2：本部门，3：全部数据）")
    private Integer scope;

    /**
     * 成员名单
     */
    @ApiModelProperty(value = "成员名单")
    private Integer userCount;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;
}

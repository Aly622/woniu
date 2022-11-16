package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色分页查询结果对象  
 * @date: 2022-02-28 17:03  
 **************************************/
@Data
public class RolePageForManageResultDTO {

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
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;

    /**
     * 是否可编辑（0：可编辑，1：不可编辑）
     */
    @ApiModelProperty(value = "是否可编辑（0：可编辑，1：不可编辑）")
    private Integer allowEdit;
}

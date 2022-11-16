package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新角色入参对象（租户端） 
 * @date: 2022-03-01 11:05  
 **************************************/
@Data
public class UpdateRoleVO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message="角色ID不能为空")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    @NotBlank(message="角色名称不能为空")
    private String name;

    /**
     * 数据范围（1：仅本人，2：本部门，3：全部数据）
     */
    @ApiModelProperty(value = "数据范围（1：仅本人，2：本部门，3：全部数据）")
    private Integer scope;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）", required = true)
    @NotNull(message="状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}

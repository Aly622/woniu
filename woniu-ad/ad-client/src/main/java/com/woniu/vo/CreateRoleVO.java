package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 创建角色入参对象（管理端） 
 * @date: 2022-03-01 10:17  
 **************************************/
@Data
public class CreateRoleVO {

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

package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 创建组织对象  
 * @date: 2022-02-25 15:30  
 **************************************/
@Data
public class CreateOrgVO {

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称", required = true)
    @NotBlank(message="组织名称不能为空")
    private String name;

    /**
     * 父组织ID
     */
    @ApiModelProperty(value = "父组织ID", required = true)
    @NotNull(message="父组织ID不能为空")
    private Long parentId;

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

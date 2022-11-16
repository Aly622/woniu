package com.woniu.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SaveTenantVO {



    /**
     * 租户名称
     */
    @ApiModelProperty(value = "租户名称",required = true)
    @NotEmpty(message="租户名称不能为空.")
    private String name;

    /**
     * 租户编号
     */
   /* @ApiModelProperty(value = "租户编号",required = true)
    @NotEmpty(message="租户编号不能为空.")
    private String code;*/


    @ApiModelProperty(value = "状态 1:启用; 0:禁用",required = true)
    @NotNull(message="状态不能为空.")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注",required = false)
    private String description;

}

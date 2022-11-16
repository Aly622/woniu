package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 租户详情对象  
 * @date: 2022-03-03 15:46  
 **************************************/
@Data
public class TenantInfoDTO {

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long id;

    /**
     * 租户名称
     */
    @ApiModelProperty(value = "租户名称")
    private String name;
}

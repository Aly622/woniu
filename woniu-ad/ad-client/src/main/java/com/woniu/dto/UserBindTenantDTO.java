package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 租户绑定对象  
 * @date: 2022-03-03 16:01  
 **************************************/
@Data
public class UserBindTenantDTO {

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID")
    private Long tenantId;

    /**
     * 是否是管理员（0：不是，1：是）
     */
    @ApiModelProperty(value = "是否是管理员（0：不是，1：是）")
    private Integer isAdmin;
}

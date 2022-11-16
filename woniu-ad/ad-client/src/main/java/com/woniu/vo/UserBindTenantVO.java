package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 账号租户绑定对象  
 * @date: 2022-03-03 11:19  
 **************************************/
@Data
public class UserBindTenantVO {

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID", required = true)
    private Long tenantId;

    /**
     * 是否是管理员（0：不是，1：是）
     */
    @ApiModelProperty(value = "是否是管理员（0：不是，1：是）", required = true)
    private Integer isAdmin;

    @Override
    public String toString() {
        if(tenantId != null) {
            return String.valueOf(tenantId);
        } else {
            return null;
        }
    }

}

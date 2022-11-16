package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 用户角色绑定对象  
 * @date: 2022-03-04 10:06  
 **************************************/
@Data
public class BindUserRoleDTO {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 租户ID
     */
    private Long tenantId;
}

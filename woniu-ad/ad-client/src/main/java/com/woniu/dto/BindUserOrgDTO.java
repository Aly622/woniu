package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 用户和组织结构绑定对象  
 * @date: 2022-03-04 11:08  
 **************************************/
@Data
public class BindUserOrgDTO {

    /**
     * 组织ID
     */
    private Long orgId;

    /**
     * 租户ID
     */
    private Long tenantId;
}

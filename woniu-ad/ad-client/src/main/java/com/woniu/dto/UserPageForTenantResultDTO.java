package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 账户分页查询结果对象（租户端） 
 * @date: 2022-03-07 11:40  
 **************************************/
@Data
public class UserPageForTenantResultDTO extends UserPageForManageResultDTO{

    /**
     * 活动授权
     */
    @ApiModelProperty(value = "活动授权")
    private Integer campaignCount;

    /**
     * 组织ID或者生产商ID
     */
    @ApiModelProperty(value = "组织ID或者生产商ID")
    private Long orgId;

    /**
     * 所属组织
     */
    @ApiModelProperty(value = "所属组织")
    private String orgName;

    /**
     * 生产商ID
     */
    @ApiModelProperty(value = "生产商ID，与前端无关")
    private Long vendId;
}

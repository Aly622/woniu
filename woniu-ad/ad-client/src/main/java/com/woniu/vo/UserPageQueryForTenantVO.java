package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 分页查询账户对象（租户端） 
 * @date: 2022-03-07 11:33  
 **************************************/
@Data
public class UserPageQueryForTenantVO extends Page {

    /**
     * 组织ID
     */
    @ApiModelProperty(value = "组织ID")
    private Long orgId;

    /**
     * orgId列表
     */
    @ApiModelProperty(value = "orgId列表，与前端无关")
    private List<Long> orgIdList;

    /**
     * 租户ID
     */
    @ApiModelProperty(value = "租户ID，与前端无关")
    private Long tenantId;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 账号类型（2：租户，3：生产商）
     */
    @ApiModelProperty(value = "账号类型（2：租户，3：生产商）", required = true)
    private Integer type;

}

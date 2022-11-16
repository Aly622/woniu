package com.woniu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 绑定角色的用户详情
 * @date: 2022-03-01 16:47  
 **************************************/
@Data
public class BindRoleUserDetailDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;
}

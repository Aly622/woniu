package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc:  账户ID和角色名称查询对象
 * @date: 2022-04-11 18:58  
 **************************************/
@Data
public class UserIdAndRoleNameQueryVO {

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID", required = true)
    @NotNull(message="账户ID不能为空")
    private Long userId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
}

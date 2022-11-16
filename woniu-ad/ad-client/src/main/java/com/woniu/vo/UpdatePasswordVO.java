package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新密码对象  
 * @date: 2022-03-29 15:05  
 **************************************/
@Data
public class UpdatePasswordVO {

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID", required = true)
    @NotNull(message="账户ID不能为空")
    private Long userId;

    /**
     * 原密码
     */
    @ApiModelProperty(value = "原密码", required = true)
    @NotBlank(message="原密码不能为空")
    private String oldPassword;

    /**
     * 新密码
     */
    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message="新密码不能为空")
    private String newPassword;
}

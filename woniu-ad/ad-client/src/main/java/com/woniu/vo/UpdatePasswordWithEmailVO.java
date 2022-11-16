package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 根据邮箱更新密码对象  
 * @date: 2022-03-29 14:51  
 **************************************/
@Data
public class UpdatePasswordWithEmailVO {

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message="邮箱不能为空")
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "邮箱验证码", required = true)
    @NotBlank(message="验证码不能为空")
    private String verificationCode;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message="密码不能为空")
    private String password;
}

package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 登录入参对象  
 * @date: 2022-03-09 16:37  
 **************************************/
@Data
public class LoginVO {

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message="用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message="密码不能为空")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    @NotBlank(message="验证码不能为空")
    private String verificationCode;
}

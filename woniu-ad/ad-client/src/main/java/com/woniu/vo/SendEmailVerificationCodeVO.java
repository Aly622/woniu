package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 发送邮箱验证码对象  
 * @date: 2022-03-29 14:39  
 **************************************/
@Data
public class SendEmailVerificationCodeVO {

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message="邮箱不能为空")
    private String email;
}

package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: 校验短信验证码入参  
 * @date: 2022-04-25 11:40  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateSmsAuthCodeVO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message="手机号不能为空")
    private String mobile;

    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码", required = true)
    @NotBlank(message="短信验证码不能为空")
    private String authCode;

}

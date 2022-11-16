package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc:  
 * @date: 2022-04-25 12:27  
 **************************************/
@Data
public class SendSmsVeriCodeVO {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message="手机号不能为空")
    private String mobile;
}

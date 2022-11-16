package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 图片验证码返回对象  
 * @date: 2022-03-31 11:16  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifiCodeDTO {

    /**
     * uuid
     */
    @ApiModelProperty(value = "uuid")
    private String uuid;

    /**
     * 图片验证码图片内容（Base64加密）
     */
    @ApiModelProperty(value = "图片验证码内容（Base64加密）")
    private String imageContent;
}

package com.woniu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 校验邮箱验证码对象  
 * @date: 2022-03-30 11:44  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateAuthCodeVO {

    /**
     * 收件人邮箱
     */
    private String tagrtEmail;

    /**
     * 邮箱验证码
     */
    private String authCode;
}

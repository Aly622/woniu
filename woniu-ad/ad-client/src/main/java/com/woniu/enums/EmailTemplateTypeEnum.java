package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc:  邮箱模板类型枚举
 * @date: 2022-03-30 11:17  
 **************************************/
@AllArgsConstructor
public enum EmailTemplateTypeEnum {

    NONE(0, "空"),
    VERIFICATION_CODE(101, "验证码"),
    SIGN_UP(102, "报名"),
    FORGET_PASSWORD(103, "忘记密码"),
    AWARD_PRIZES(201, "发奖"),
    REISSUE_PRIZES(202, "补发奖品");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

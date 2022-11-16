package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 状态类型
 * @date: 2019-08-16 09:47 
 **************************************/
@AllArgsConstructor
public enum UserStatusEnum {

    STATUS_UNABLE(0, "不可用"),
    STATUS_ABLE(1, "可用");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

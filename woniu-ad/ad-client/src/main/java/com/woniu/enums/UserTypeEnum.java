package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 会员类型
 * @date: 2019-08-16 09:47 
 **************************************/
@AllArgsConstructor
public enum UserTypeEnum {

    USER_TYPE_PLATFORM(1, "平台级账号"),
    USER_TYPE_TENANT(2, "租户级账号"),
    USER_TYPE_MANUFACTURER(3, "生产商账号");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

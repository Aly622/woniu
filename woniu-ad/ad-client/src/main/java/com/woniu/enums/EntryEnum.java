package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 状态类型
 * @date: 2019-08-16 09:47 
 **************************************/
@AllArgsConstructor
public enum EntryEnum {

    B_PLATFORM_END(1, "平台端"),
    B_TENANT_END(2, "租户端"),
    C_END(3, "C端");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 会员类型
 * @date: 2019-08-16 09:47 
 **************************************/
@AllArgsConstructor
public enum IsAdminEnum {

    IS_ADMIN_NO(0, "不是管理员"),
    IS_ADMIN_YES(1, "是管理员");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

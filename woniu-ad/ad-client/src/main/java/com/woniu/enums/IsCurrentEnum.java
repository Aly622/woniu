package com.woniu.enums;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 会员类型
 * @date: 2019-08-16 09:47 
 **************************************/
@AllArgsConstructor
public enum IsCurrentEnum {

    IS_CURRENT_NO(0, "不是当前部门"),
    IS_CURRENT_YES(1, "是当前部门");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

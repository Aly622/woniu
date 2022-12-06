package com.woniu.response;

import lombok.AllArgsConstructor;

/************************************** 
 * @program: connexus
 * @author: mike.ma
 * @desc: 部门Response代码  
 * @date: 2019-07-09 15:02  
 **************************************/
@AllArgsConstructor
public enum GwResponseCode implements IResponseCode {
    ILLEGAL_ENTRY(11001, "非法访问,确实调用方标识！"),
    TENANTID_CAN_NOT_BE_EMPTY(11002, "租户号不允许为空！"),
    TOKEN_IS_EXPIRED(11003, "Token已过期！"),
    ILLEGAL_TOKEN(11004, "非法Token！"),
    TOKEN_CAN_NOT_BE_EMPTY(11005, "Token不能为空！"),

    SUCCESS(0, "用户成功请求"),
    FAIL(-1, "用户请求失败");
    private long code;
    private String message;

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

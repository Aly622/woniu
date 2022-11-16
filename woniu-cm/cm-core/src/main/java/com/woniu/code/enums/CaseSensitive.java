package com.woniu.code.enums;

import lombok.AllArgsConstructor;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName CaseSensitive.java
 * @Description TODO
 * @createTime 2022年03月12日 22:26:00
 */
@AllArgsConstructor
public enum CaseSensitive {
    UPPERCASE(1, "纯大写"),
    LOWERCASE(2, "纯小写");

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

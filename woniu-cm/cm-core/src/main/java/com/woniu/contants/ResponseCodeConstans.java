package com.woniu.contants;

import com.woniu.response.IResponseCode;

import lombok.AllArgsConstructor;

/**
 * @InterfaceName ResponseCodeConstans
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/6/11 23:00
 * @Version 1.0
 **/
@AllArgsConstructor
public enum ResponseCodeConstans implements IResponseCode {
    SUCCESS_CODE(0, "操作成功"),
    FAIL_CODE(-1, "用户请求失败"),
    PARAM_EXCEPTION_CODE(-2, "参数绑定异常: "),
    VIEW_EXCEPTION_CODE(-3, ""),
    SERVICE_EXCEPTION_CODE(-4, ""),
    SYSTEM_EXCEPTION_CODE(-5, "系统层异常:"),
    NOT_FOUND_CODE(-6, "找不到资源");


    //    VIEW_EXCEPTION_CODE(-4, "-->视图校验层异常"),
    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

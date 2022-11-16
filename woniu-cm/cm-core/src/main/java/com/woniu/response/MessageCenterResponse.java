package com.woniu.response;

import com.alibaba.fastjson.JSON;
import com.woniu.contants.ResponseCodeConstans;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 消息中心响应对象  
 * @date: 2022-03-30 10:51  
 **************************************/
@Data
public class MessageCenterResponse<T> implements Serializable {
    private static final long serialVersionUID = 515730635420960456L;
    @ApiModelProperty(value = "跟踪ID")
    private String traceId;
    @ApiModelProperty(value = "是否成功（1：成功，0：失败）")
    private Boolean isSuccess;
    @ApiModelProperty(value = "返回代码（0：成功，其他：失败）")
    private int code;
    @ApiModelProperty(value = "错误信息")
    private String message;
    @ApiModelProperty(value = "结果对象")
    private T result;

    @Builder  //@Builder注解修饰类时，该类将没有无参构造方法
    public MessageCenterResponse(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public MessageCenterResponse() {
    }

    public MessageCenterResponse(T result) {
        this.code = ResponseCodeConstans.SUCCESS_CODE.getCode();
        this.message = ResponseCodeConstans.SUCCESS_CODE.getMessage();
        this.result = result;
    }

    /*
     * @Author Oliver.Liu
     * @Desc 有返回值的构造器
     * @Date 2019/6/7 14:18
     * @Param [response, result]
     * @return
     **/
    public MessageCenterResponse(IResponseCode responseCode, T result) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.result = result;
        this.isSuccess = successOrfail();
    }

    /*
     * @Author Oliver.Liu
     * @Desc 带前缀字符串的构造器
     * @Date 2020/5/9 14:18
     * @Param [response, prefix]
     * @return
     **/
    public MessageCenterResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.isSuccess = successOrfail();
    }

    /*
     * @Author Oliver.Liu
     * @Desc 带前缀字符串的构造器
     * @Date 2020/5/9 14:18
     * @Param [response, prefix]
     * @return
     **/
    public MessageCenterResponse(String prefix, IResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = prefix + responseCode.getMessage();
        this.isSuccess = successOrfail();
    }

    /*
     * @Author Oliver.Liu
     * @Desc 无返回数据的构造器
     * @Date 2019/6/7 14:17
     * @Param [response]
     * @return
     **/
    public MessageCenterResponse(IResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.isSuccess = successOrfail();
    }

    public static WebResponse success(String msg, Object data) {
        return new WebResponse(ResponseCodeConstans.SUCCESS_CODE.getCode(), msg, data);
    }

    public static WebResponse success(String msg) {
        return new WebResponse(ResponseCodeConstans.SUCCESS_CODE.getCode(), msg);
    }

    public static WebResponse success() {
        return new WebResponse(ResponseCodeConstans.SUCCESS_CODE);
    }

    public static WebResponse fail(IResponseCode responseCode, String msg) {
        return new WebResponse(responseCode.getCode(), msg);
    }

    public static WebResponse fail(String msg) {
        return new WebResponse(ResponseCodeConstans.FAIL_CODE.getCode(), msg);
    }

    public static WebResponse notFound() {
        return new WebResponse(ResponseCodeConstans.NOT_FOUND_CODE);
    }

    /*
     * @Author Oliver.Liu
     * @Desc 请求是否成功
     * @Date 2019/6/6 17:28
     * @Param []
     * @return java.lang.Boolean
     **/
    public Boolean successOrfail() {
        return ResponseCodeConstans.SUCCESS_CODE.getCode() == this.getCode();
    }

    /*
     * @Author Oliver.Liu
     * @Desc 转换字符串
     * @Date 2019/6/7 14:10
     * @Param []
     * @return java.lang.String
     **/
    @Override
    public String toString() {
        String jsonStr = JSON.toJSONString(this);
        return jsonStr;
    }
}

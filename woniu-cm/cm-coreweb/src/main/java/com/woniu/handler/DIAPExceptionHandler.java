package com.woniu.handler;

import com.woniu.contants.ResponseCodeConstans;
import com.woniu.exception.ServiceException;
import com.woniu.response.IResponseCode;
import com.woniu.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName GlobalExceptionHandler
 * @Desc 通用Api Controller全局异常处理类
 * @Author Oliver.Liu
 * @Date 2019/6/9 18:17
 * @Version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class DIAPExceptionHandler {
    /*
     * @Author Oliver.Liu
     * @Desc 自定义 REST 业务异常
     * @Date 2019/6/9 18:19
     * @Param [e]
     * @return R<Object>
     **/
    @ExceptionHandler(value = Exception.class)
    public WebResponse errorHandler(Exception e) {
        /*
         * 对象属性校验异常
         */
        if(e instanceof MethodArgumentNotValidException) {
            StringBuffer errMsg = new StringBuffer();
            BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) ((MethodArgumentNotValidException) e).getBindingResult();
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(err->{
                errMsg.append(err.getDefaultMessage());
                errMsg.append("\n");
            });
            log.error("视图校验异常: {}", e);
            return new WebResponse(errMsg.toString(), ResponseCodeConstans.VIEW_EXCEPTION_CODE);
        }
        /*
         * 业务逻辑异常
         */
        if (e instanceof ServiceException) {
            IResponseCode errorCode = ((ServiceException) e).getResponseCode();
            log.error("业务异常: {}", e);
            return new WebResponse(ResponseCodeConstans.SERVICE_EXCEPTION_CODE.getMessage(), errorCode);
        }
        /*
         * 参数校验异常
         */
        if (e instanceof BindException) {
            BindingResult bindingResult = ((BindException) e).getBindingResult();
            if (null != bindingResult && bindingResult.hasErrors()) {
                List<Object> jsonList = new ArrayList<>();
                bindingResult.getFieldErrors().stream().forEach(fieldError -> {
                    Map<String, Object> jsonObject = new HashMap<>(2);
                    jsonObject.put("fieldname", fieldError.getField());
                    jsonObject.put("message", fieldError.getDefaultMessage());
                    jsonList.add(jsonObject);
                });
                return new WebResponse<List<Object>>(ResponseCodeConstans.PARAM_EXCEPTION_CODE, jsonList);
            }
        }
        /**
         * 系统内部异常，打印异常栈
         */
        log.error("系统异常: {}", e);
        return new WebResponse(ResponseCodeConstans.SYSTEM_EXCEPTION_CODE.getCode(),
                        ResponseCodeConstans.SYSTEM_EXCEPTION_CODE.getMessage() + ": " + e.getMessage());
    }
}

package com.woniu.service;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: SmsVerificationCodeService  
 * @date: 2022-04-25 10:57  
 **************************************/
public interface SmsVerificationCodeService {

    /**
     * @param mobile
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 发送短信验证码
     * @date: 2022/4/25 11:46
     */
    Boolean sendSmsVerificationCode(String mobile);
}

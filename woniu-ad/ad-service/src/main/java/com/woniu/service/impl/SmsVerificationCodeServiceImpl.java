package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.woniu.client.UcMessageCenterClient;
import com.woniu.entity.UcUser;
import com.woniu.enums.SmsTemplateTypeEnum;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.esmartwave.niumeng.diap.response.IResponseCode;
import com.esmartwave.niumeng.diap.response.MessageCenterResponse;
import com.woniu.response.UCResponseCode;
import com.woniu.service.SmsVerificationCodeService;
import com.woniu.service.UcUserService;
import com.woniu.vo.SendSmsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: 验证码发送Service实现类  
 * @date: 2022-04-25 10:59  
 **************************************/
@Slf4j
@Service
public class SmsVerificationCodeServiceImpl implements SmsVerificationCodeService {

    @Autowired
    private UcMessageCenterClient ucMessageCenterClient;

    @Autowired
    private UcUserService ucUserService;

    @Override
    public Boolean sendSmsVerificationCode(String mobile) {
        UcUser ucUser = ucUserService.getUcUserByMobile(mobile);
        if(ucUser == null) {
            throw new ServiceException(UCResponseCode.DONT_HAVE_USER_WITH_MOBILE);
        }
        SendSmsVO sendSms = new SendSmsVO(SmsTemplateTypeEnum.VERIFICATION_CODE.getCode(), mobile);
        log.info("#### 发送短信验证码参数：{}", JSON.toJSONString(sendSms));
        MessageCenterResponse<Boolean> response = ucMessageCenterClient.sendSms(sendSms);
        log.info("#### 发送短信验证码返回值：{}", JSON.toJSONString(response));
        if(response == null || response.getIsSuccess() == null || !response.getIsSuccess()) {
            throw new ServiceException(new IResponseCode() {
                public int getCode() {
                    return response.getCode();
                }
                public String getMessage() {
                    return response.getMessage();
                }
            });
        }
        return true;
    }

}

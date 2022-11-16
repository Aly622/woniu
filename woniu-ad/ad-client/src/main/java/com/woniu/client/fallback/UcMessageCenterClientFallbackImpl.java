package com.woniu.client.fallback;

import com.woniu.client.UcMessageCenterClient;
import com.esmartwave.niumeng.diap.response.MessageCenterResponse;
import com.woniu.vo.SendEmailVO;
import com.woniu.vo.SendSmsVO;
import com.woniu.vo.ValidateAuthCodeVO;
import com.woniu.vo.ValidateSmsAuthCodeVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Component
public class UcMessageCenterClientFallbackImpl implements UcMessageCenterClient {

    @Setter
    private Throwable cause;

    @Override
    public MessageCenterResponse<Boolean> sendEmail(SendEmailVO sendEmail) {
        log.error("#### feign 发送邮件参数为：{}", ToStringBuilder.reflectionToString(sendEmail), cause);
        return null;
    }

    @Override
    public MessageCenterResponse<Boolean> validateAuthCode(ValidateAuthCodeVO validateAuthCode) {
        log.error("#### feign 校验邮箱验证码参数为：{}", ToStringBuilder.reflectionToString(validateAuthCode), cause);
        return null;
    }

    @Override
    public MessageCenterResponse<Boolean> sendSms(SendSmsVO sendSms) {
        log.error("#### feign 发送短信参数为：{}", ToStringBuilder.reflectionToString(sendSms), cause);
        return null;
    }

    @Override
    public MessageCenterResponse<Boolean> validateSmsAuthCode(@RequestBody ValidateSmsAuthCodeVO validateSmsAuthCode) {
        log.error("#### feign 校验短信验证码参数为：{}", ToStringBuilder.reflectionToString(validateSmsAuthCode), cause);
        return null;
    }
}

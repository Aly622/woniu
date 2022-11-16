package com.woniu.client;


import com.woniu.client.factory.UcMessageCenterClientFallbackFactory;
import com.esmartwave.niumeng.diap.contants.ServiceNameConstants;
import com.esmartwave.niumeng.diap.response.MessageCenterResponse;
import com.woniu.vo.SendEmailVO;
import com.woniu.vo.SendSmsVO;
import com.woniu.vo.ValidateAuthCodeVO;
import com.woniu.vo.ValidateSmsAuthCodeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = ServiceNameConstants.DIAP_MESSAGECENTER, fallbackFactory = UcMessageCenterClientFallbackFactory.class)
public interface UcMessageCenterClient {

    /**
     * @param sendEmail
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.response.MessageCenterResponse<java.lang.Boolean>
     * @desc: 发送邮件
     * @date: 2022/3/30 10:57
     */
    @PostMapping("/mc/v1/Email/Send")
    MessageCenterResponse<Boolean> sendEmail(@RequestBody SendEmailVO sendEmail);

    /**
     * @param validateAuthCode
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.response.MessageCenterResponse<java.lang.Boolean>
     * @desc: 校验邮箱验证码
     * @date: 2022/3/30 11:46
     */
    @PostMapping("/mc/v1/Email/Validate")
    MessageCenterResponse<Boolean> validateAuthCode(@RequestBody ValidateAuthCodeVO validateAuthCode);

    /**
     * @param sendSms
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.response.MessageCenterResponse<java.lang.Boolean>
     * @desc: 发送短信
     * @date: 2022/4/25 11:38
     */
    @PostMapping("/mc/v1/Sms/Send")
    MessageCenterResponse<Boolean> sendSms(@RequestBody SendSmsVO sendSms);

    /**
     * @param validateSmsAuthCode
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.response.MessageCenterResponse<java.lang.Boolean>
     * @desc: 校验短信验证码
     * @date: 2022/4/25 11:42
     */
    @PostMapping("/mc/v1/Sms/Validate")
    MessageCenterResponse<Boolean> validateSmsAuthCode(@RequestBody ValidateSmsAuthCodeVO validateSmsAuthCode);
}

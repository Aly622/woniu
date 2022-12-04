package com.woniu.controller;

import com.woniu.dto.VerifiCodeDTO;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.SmsVerificationCodeService;
import com.woniu.vo.SendSmsVeriCodeVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/************************************** 
 * @program: sh-diap
 * @author: mike.ma
 * @desc: 短信验证码Controller  
 * @date: 2022-04-25 10:43  
 **************************************/
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/smsVeriCode")
public class SmsVerificationCodeController extends SuperController {

    @Autowired
    private SmsVerificationCodeService smsVerificationCodeService;

    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/send")
    public WebResponse<VerifiCodeDTO> sendSmsVerifiCode(@RequestBody SendSmsVeriCodeVO sendSmsVeriCode) {
        log.info("#### 发送短信验证码入参：{}", ToStringBuilder.reflectionToString(sendSmsVeriCode));
        Boolean sendFlag = smsVerificationCodeService.sendSmsVerificationCode(sendSmsVeriCode.getMobile());
        if(sendFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }
}

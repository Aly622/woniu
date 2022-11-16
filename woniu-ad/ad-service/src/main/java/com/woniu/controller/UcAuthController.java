package com.woniu.controller;

import com.woniu.constants.UcConstants;
import com.woniu.dto.LoginResultDTO;
import com.woniu.enums.LoginTypeEnum;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcAuthService;
import com.woniu.utils.RedisUtils;
import com.woniu.vo.LoginVO;
import com.woniu.vo.ValidateSmsAuthCodeVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc:  授权登录Controller
 * @date: 2022-03-09 15:05  
 **************************************/
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/auth")
public class UcAuthController extends SuperController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UcAuthService ucAuthService;

    @ApiOperation("登录授权")
    @PostMapping("/login")
    public WebResponse<LoginResultDTO> login(@RequestBody @Valid LoginVO login, @RequestHeader("Entry") Integer entry, HttpServletRequest request) throws Exception{
        log.info("#### 登录授权入参：{}", ToStringBuilder.reflectionToString(login));
        request.setCharacterEncoding("utf-8");
        String verifyKey = UcConstants.CAPTCHA_CODE_KEY + login.getUuid();
        //从redis中获取真正的验证码
        String redisVerificationCode = redisUtils.getString(verifyKey);
        log.info("#### 登录授权获取redis中的验证码为：redisVerificationCode = {}", redisVerificationCode);
        if(!login.getVerificationCode().equalsIgnoreCase(redisVerificationCode)) {
            throw new ServiceException(UCResponseCode.AUTH_CODE_IS_ERROR);
        }
        return new WebResponse(UCResponseCode.SUCCESS, ucAuthService.login(login.getUserName(), login.getPassword(), null, null, LoginTypeEnum.USERNAME_PASSWORD_TYPE.ordinal(), entry));
    }

    @ApiOperation("手机验证码登录授权")
    @PostMapping("/sms/login")
    public WebResponse<LoginResultDTO> smsLogin(@RequestBody @Valid ValidateSmsAuthCodeVO validateSmsAuthCode, @RequestHeader("Entry") Integer entry, HttpServletRequest request) throws Exception{
        log.info("#### 手机验证码登录授权入参：{}", ToStringBuilder.reflectionToString(validateSmsAuthCode));
        return new WebResponse(UCResponseCode.SUCCESS, ucAuthService.login(null, null, validateSmsAuthCode.getMobile(), validateSmsAuthCode.getAuthCode(), LoginTypeEnum.MOBILE_TYPE.ordinal(), entry));
    }

}

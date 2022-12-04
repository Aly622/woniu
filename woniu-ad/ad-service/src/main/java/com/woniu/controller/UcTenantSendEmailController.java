package com.woniu.controller;


import com.woniu.entity.UcTenantSendEmail;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcTenantSendEmailService;
import com.woniu.vo.TenantSendEmailVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 租户表发送邮件邮箱配置表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/ucTenantSendEmail")
public class UcTenantSendEmailController extends SuperController {

    @Autowired
    private UcTenantSendEmailService service;

    @ApiOperation("配置存租户发送预警邮件邮箱")
    @PostMapping("/saveTenantSendEmail")
    public WebResponse saveTenantSendEmail(@RequestBody @Valid TenantSendEmailVO tenantSendEmailVO){
        log.info("########## 配置存租户发送预警邮件邮箱 入参:{}:", ToStringBuilder.reflectionToString(tenantSendEmailVO));
        boolean isSuccess = service.saveTenantSendEmail(tenantSendEmailVO);
        if (isSuccess) return new WebResponse(UCResponseCode.SUCCESS);
        return new WebResponse(UCResponseCode.FAIL);
    }

    @ApiOperation("获取租户发送预警邮件邮箱")
    @GetMapping("/getAllTenantEmail")
    public  WebResponse<List<UcTenantSendEmail>> getAllTenantEmail(){
        List<UcTenantSendEmail> list = service.getAllTenantEmail();
        return new WebResponse<List<UcTenantSendEmail>>(UCResponseCode.SUCCESS,list);
    }

    @ApiOperation("根据租户id获取租户发送预警邮件邮箱")
    @GetMapping("/getEmailByTenantId/{tenantId}")
    public  WebResponse<List<UcTenantSendEmail>> getEmailByTenantId(@PathVariable("tenantId") Long tenantId){
        List<UcTenantSendEmail> list = service.getEmailByTenantId(tenantId);
        return new WebResponse<List<UcTenantSendEmail>>(UCResponseCode.SUCCESS,list);
    }

    



}


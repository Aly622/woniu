package com.woniu.controller;


import com.woniu.entity.UcTenantWchatRelation;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcTenantWchatRelationService;
import com.woniu.vo.AuthorizaWechatVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.esmartwave.niumeng.diap.controller.SuperController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 租户微信权表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-03-28
 */
@Api(tags = "租户微信授权 相关接口")
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/ucTenantWchatRelation")
public class UcTenantWchatRelationController extends SuperController {

    @Autowired
    private UcTenantWchatRelationService service;

    //租户微信授权
    @ApiOperation("租户微信授权")
    @PostMapping("/authorizaWechat")
    public WebResponse authorizaWechat(@RequestBody @Valid AuthorizaWechatVO authorizaWechatVO){
        log.info("########## 租户微信授权 入参:{}:", ToStringBuilder.reflectionToString(authorizaWechatVO));
        boolean isSuccess = service.authorizaWechat(authorizaWechatVO);
        if (isSuccess) return new WebResponse(UCResponseCode.SUCCESS);
        return new WebResponse(UCResponseCode.FAIL);
    }

    @ApiOperation("获取租户下微信列表")
    @GetMapping("/getAuthorizaWechatList")
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatList(){
        List<UcTenantWchatRelation> list = service.list();
        return new WebResponse<List<UcTenantWchatRelation>>(UCResponseCode.SUCCESS,list);
    }

    @ApiOperation("根据租户id获取租户下微信列表")
    @GetMapping("/getAuthorizaWechatByTenantId/{tenantId}")
    public WebResponse<List<UcTenantWchatRelation>> getAuthorizaWechatByTenantId(@PathVariable("tenantId")Long tenantId){
        List<UcTenantWchatRelation> list = service.getAuthorizaWechatByTenantId(tenantId);
        return new WebResponse<List<UcTenantWchatRelation>>(UCResponseCode.SUCCESS,list);
    }


}


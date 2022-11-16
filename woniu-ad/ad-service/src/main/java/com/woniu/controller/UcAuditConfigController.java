package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.AuditConfigDTO;
import com.woniu.entity.UcAuditConfig;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcAuditConfigService;
import com.woniu.vo.AuditConfigPageQueryVO;
import com.woniu.vo.CreateAuditConfigVO;
import com.woniu.vo.UpdateAuditConfigVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 审批流配置表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/auditConfig")
public class UcAuditConfigController extends SuperController {

    @Autowired
    private UcAuditConfigService ucAuditConfigService;

    @ApiOperation(value = "分页查询审批流配置")
    @PostMapping("/getAuditConfigPageResult")
    public WebResponse<Page<UcAuditConfig>> getAuditConfigPageResult(@RequestBody AuditConfigPageQueryVO auditConfigPageQuery) throws Exception {
        log.info("#### 分页查询角色列表（租户端）入参：{}", ToStringBuilder.reflectionToString(auditConfigPageQuery));
        Page<UcAuditConfig> page = ucAuditConfigService.selectAuditConfigPageResult(auditConfigPageQuery);
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "创建审批流")
    @PostMapping("/createAuditConfig")
    public WebResponse<Page<UcAuditConfig>> createAuditConfig(@RequestBody @Valid CreateAuditConfigVO createAuditConfig) throws Exception {
        log.info("#### 创建审批流入参：{}", ToStringBuilder.reflectionToString(createAuditConfig));
        Boolean createFlag = ucAuditConfigService.createAuditConfig(createAuditConfig);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "获取审批流详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="审批流ID",required=true),
    })
    @GetMapping("/getAuditConfigDetail/{id}")
    public WebResponse<AuditConfigDTO> getAuditConfigDetail(@PathVariable("id") Long id) throws Exception {
        log.info("#### 获取审批流详情入参：id = {}", id);
        AuditConfigDTO auditConfig = ucAuditConfigService.selectAuditConfigDetail(id);
        return new WebResponse(UCResponseCode.SUCCESS, auditConfig);
    }

    @ApiOperation(value = "更新审批流")
    @PostMapping("/updateAuditConfig")
    public WebResponse updateAuditConfig(@RequestBody @Valid UpdateAuditConfigVO updateAuditConfig) throws Exception {
        log.info("#### 更新审批流入参：{}", ToStringBuilder.reflectionToString(updateAuditConfig));
        Boolean updateFlag = ucAuditConfigService.updateAuditConfig(updateAuditConfig);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }
}


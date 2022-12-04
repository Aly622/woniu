package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.TenantInfoDTO;
import com.woniu.entity.UcTenant;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.TenantService;
import com.woniu.service.UcTenantService;
import com.woniu.utils.ObjectCopier;
import com.woniu.vo.SaveTenantVO;
import com.woniu.vo.TenantPageVO;
import com.woniu.vo.UpdateTenantVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Api(tags = "租户管理 相关接口")
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/tenant")
public class UcTenantController extends SuperController {

    @Autowired
    private TenantService service;

    @Autowired
    private UcTenantService ucTenantService;


    @ApiOperation("新增租户")
    @PostMapping("/saveTenant")
    public WebResponse saveTenant(@RequestBody @Valid SaveTenantVO saveTenantVO) throws Exception{
        log.info("########## 新增租户 入参:{}:", ToStringBuilder.reflectionToString(saveTenantVO));
        UcTenant ucTenant = ObjectCopier.copyObject(saveTenantVO, UcTenant.class);
        boolean isSuccess = service.saveTenant(ucTenant);
        if (isSuccess)return new WebResponse(UCResponseCode.SUCCESS);
        return new WebResponse(UCResponseCode.FAIL);
    }


    @ApiOperation("编辑租户")
    @PostMapping("/updateTenant")
    public WebResponse updateTenant(@RequestBody @Valid UpdateTenantVO updateTenantVO) throws Exception{
        log.info("########## 编辑租户 入参:{}:", ToStringBuilder.reflectionToString(updateTenantVO));
        UcTenant ucTenant = ObjectCopier.copyObject(updateTenantVO, UcTenant.class);
        boolean isSuccess = service.updateTenant(ucTenant);
        if (isSuccess)return new WebResponse(UCResponseCode.SUCCESS);
        return new WebResponse(UCResponseCode.FAIL);
    }

    @ApiOperation("根据id获取租户信息")
    @ApiImplicitParam(name = "id",value = "租户id",required = true)
    @GetMapping("/getTenantById/{id}")
    public WebResponse<UcTenant> getTenantById(@PathVariable("id") Long id) throws Exception{
        log.info("########## 根据id获取租户信息 入参:{}:", ToStringBuilder.reflectionToString(id));
        return new WebResponse<UcTenant>(UCResponseCode.SUCCESS,ucTenantService.getById(id));
    }
    @ApiOperation("分页获取租户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "租户名称",required = false),
            @ApiImplicitParam(name = "current",value = "页码",required = false),
            @ApiImplicitParam(name = "size",value = "每页条数",required = false),

    })
    @PostMapping("/tenantPage")
    public WebResponse<Page<UcTenant>> tenantPage(@RequestBody  TenantPageVO tenantPageVO) throws Exception{
        log.info("########## 分页获取租户列表 入参:{}:", ToStringBuilder.reflectionToString(tenantPageVO));
        Page<UcTenant> page = ucTenantService.tenantPage(tenantPageVO);
        return new WebResponse<Page<UcTenant>>(UCResponseCode.SUCCESS,page);

    }

    @ApiOperation("获取所有租户信息")
    @GetMapping("/getAllTenant")
    public WebResponse<List<TenantInfoDTO>> getAllTenant() throws Exception{
        List<TenantInfoDTO> tenantInfoList = ucTenantService.selectAllTenant();
        return new WebResponse(UCResponseCode.SUCCESS, tenantInfoList);
    }

    @ApiOperation("获取所有租户端的租户信息")
    @GetMapping("/getTenantSideTenant")
    public WebResponse<List<TenantInfoDTO>> getTenantSideTenant() throws Exception{
        List<TenantInfoDTO> tenantInfoList = ucTenantService.getTenantSideTenant();
        return new WebResponse(UCResponseCode.SUCCESS, tenantInfoList);
    }
}


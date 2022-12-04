package com.woniu.controller;


import com.woniu.dto.PermIdsDTO;
import com.woniu.extend.TokenUser;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcTenantPermRelationService;
import com.woniu.utils.SecurityUtils;
import com.woniu.vo.TenantPermVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Api(tags = "租户菜单授券 相关接口")
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/tenantPermRelation")
public class UcTenantPermRelationController extends SuperController {

    @Autowired
    private UcTenantPermRelationService service;

    @ApiOperation("租户菜单授券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId",value = "租户id",required = true),
            @ApiImplicitParam(name = "permIds",value = "权限id集合",required = true),
    })
    @PostMapping("/ternantPerm")
    public WebResponse ternantPerm(@RequestBody TenantPermVO tenantPermVO) throws Exception{
        log.info("########## 编辑租户 入参:{}:", ToStringBuilder.reflectionToString(tenantPermVO));
       boolean isSuccess = service.ternantPerm(tenantPermVO);
        return new WebResponse(UCResponseCode.SUCCESS);
    }


    @ApiOperation("根据租户id获取租户下所有权限id")
    @GetMapping("/getTenantAllPermByTenantId/{tenantId}")
    public WebResponse<PermIdsDTO> getTenantAllPermByTenantId(@PathVariable("tenantId") Long tenantId ){
        TokenUser user = SecurityUtils.getUser();
        List<Long> permIds =  service.getTenantAllPermByTenantId(tenantId);
        PermIdsDTO permIdsDTO = new PermIdsDTO();
        permIdsDTO.setPermIds(permIds);

        return  new WebResponse<PermIdsDTO>(UCResponseCode.SUCCESS, permIdsDTO);
    }

    @ApiOperation("获取租户下所有权限id")
    @GetMapping("/getTenantAllPerm")
    public WebResponse<PermIdsDTO> getTenantAllPerm(){
        TokenUser user = SecurityUtils.getUser();
        List<Long> permIds =  service.getTenantAllPerm();
        PermIdsDTO permIdsDTO = new PermIdsDTO();
        permIdsDTO.setPermIds(permIds);
        return  new WebResponse<PermIdsDTO>(UCResponseCode.SUCCESS,permIdsDTO);
    }




}


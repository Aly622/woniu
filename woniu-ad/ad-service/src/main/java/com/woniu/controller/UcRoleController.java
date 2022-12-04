package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dto.*;
import com.woniu.entity.UcRole;
import com.woniu.extend.TokenUser;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcRoleService;
import com.woniu.utils.SecurityUtils;
import com.woniu.vo.*;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/role")
public class UcRoleController extends SuperController {

    @Autowired
    private UcRoleService ucRoleService;

    @ApiOperation(value = "分页查询角色列表（租户端）")
    @PostMapping("/getRolePageForTenant")
    public WebResponse<Page<RolePageForTenantResultDTO>> getRolePageForTenant(@RequestBody RolePageQueryVO rolePageQuery) throws Exception {
        log.info("#### 分页查询角色列表（租户端）入参：{}", ToStringBuilder.reflectionToString(rolePageQuery));
        Page<RolePageForTenantResultDTO> page = ucRoleService.selectRolePageForTenant(rolePageQuery);
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "分页查询角色列表（管理端）")
    @PostMapping("/getRolePageForManage")
    public WebResponse<Page<RolePageForManageResultDTO>> getRolePageForManage(@RequestBody RolePageQueryVO rolePageQuery) throws Exception {
        log.info("#### 分页查询角色列表（管理端）入参：{}", ToStringBuilder.reflectionToString(rolePageQuery));
        Page<RolePageForManageResultDTO> page = ucRoleService.selectRolePageForManage(rolePageQuery);
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "创建角色（租户端）")
    @PostMapping("/createRoleForTenant")
    public WebResponse createRoleForTenant(@RequestBody @Valid CreateRoleVO createRole) throws Exception {
        log.info("#### 创建角色（租户端）入参：{}", ToStringBuilder.reflectionToString(createRole));
        if(createRole.getScope() == null) {
            return new WebResponse(UCResponseCode.DATA_SCOPE_IS_NULL);
        }
        Boolean createFlag = ucRoleService.createRole(createRole);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "创建角色（管理端）")
    @PostMapping("/createRoleForManage")
    public WebResponse createRoleForManage(@RequestBody @Valid CreateRoleVO createRole) throws Exception {
        log.info("#### 创建角色（管理端）入参：{}", ToStringBuilder.reflectionToString(createRole));
        Boolean createFlag = ucRoleService.createRole(createRole);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "获取角色详情（租户端和管理端公用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="角色ID",required=true),
    })
    @GetMapping("/getUcRoleDetail/{id}")
    public WebResponse<UcRole> getUcRoleDetail(@PathVariable("id") Long id) throws Exception {
        log.info("#### 获取角色详情（租户端和管理端公用）入参：id = {}", id);
        UcRole ucRole = ucRoleService.getUcRoleDetail(id);
        return new WebResponse(UCResponseCode.SUCCESS, ucRole);
    }

    @ApiOperation(value = "更新角色（租户端）")
    @PostMapping("/updateRoleForTenant")
    public WebResponse updateRoleForTenant(@RequestBody @Valid UpdateRoleVO updateRole) throws Exception {
        log.info("#### 更新角色（租户端）入参：{}", ToStringBuilder.reflectionToString(updateRole));
        if(updateRole.getScope() == null) {
            return new WebResponse(UCResponseCode.DATA_SCOPE_IS_NULL);
        }
        Boolean updateFlag = ucRoleService.updateRole(updateRole);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "更新角色（管理端）")
    @PostMapping("/updateRoleForManage")
    public WebResponse updateRoleForManage(@RequestBody @Valid UpdateRoleVO updateRole) throws Exception {
        log.info("#### 更新角色（管理端）入参：{}", ToStringBuilder.reflectionToString(updateRole));
        Boolean updateFlag = ucRoleService.updateRole(updateRole);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "删除角色（租户端和管理端公用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="角色ID",required=true),
    })
    @DeleteMapping("/deleteRole/{id}")
    public WebResponse deleteOrg(@PathVariable("id") Long id) throws Exception {
        log.info("#### 删除角色（租户端和管理端公用）入参：id = {}", id);
        Boolean deleteFlag = ucRoleService.deleteRole(id);
        if(deleteFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "获取权限树列表（租户端和管理端公用）")
    @GetMapping("/getPermissionTreeList")
    public WebResponse<List<PermissionTreeDTO>> getPermissionTreeList() throws Exception {
        TokenUser user = SecurityUtils.getUser();
        List<PermissionTreeDTO> permissionTreeList = ucRoleService.getPermissionTreeList(user.getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, permissionTreeList);
    }

    @ApiOperation(value = "创建或更新角色与权限绑定（租户端和管理端公用）")
    @PostMapping("/createOrUpdateRolePermissionRelation")
    public WebResponse createRolePermissionRelation(@RequestBody @Valid CreateOrUpdateRolePermissionRelationVO createOrUpdateRolePermissionRelation) throws Exception {
        log.info("#### 创建或更新角色与权限绑定（租户端和管理端公用）入参：{}", ToStringBuilder.reflectionToString(createOrUpdateRolePermissionRelation));
        Boolean createFlag = ucRoleService.createRolePermissionRelation(createOrUpdateRolePermissionRelation);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "根据角色ID获取权限ID列表（租户端和管理端公用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="角色ID",required=true),
    })
    @GetMapping("/getPermissionIdListByRoleId/{id}")
    public WebResponse<PermissionIdListDTO> getPermissionIdListByRoleId(@PathVariable("id") Long id) throws Exception {
        log.info("#### 根据角色ID获取权限ID列表（租户端和管理端公用）入参：id = {}", id);
        List<Long> permissionIdList = ucRoleService.getPermissionIdListByRoleId(id);
        return new WebResponse(UCResponseCode.SUCCESS, new PermissionIdListDTO(permissionIdList));
    }

    @ApiOperation(value = "查询角色（租户端和管理端公用）")
    @PostMapping("/selectAllRole")
    public WebResponse<List<RoleInfoDTO>> selectAllRole(@RequestBody RoleQueryVO roleQuery) throws Exception {
        List<RoleInfoDTO> roleInfoList = ucRoleService.selectAllRole(roleQuery);
        return new WebResponse(UCResponseCode.SUCCESS, roleInfoList);
    }

}


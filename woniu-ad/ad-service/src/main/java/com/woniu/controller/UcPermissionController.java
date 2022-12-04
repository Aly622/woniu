package com.woniu.controller;


import com.woniu.dto.PermissionTreeForLoginDTO;
import com.woniu.extend.TokenUser;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcPermissionService;
import com.woniu.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/permission")
public class UcPermissionController extends SuperController {

    @Autowired
    private UcPermissionService ucPermissionService;

    @ApiOperation(value = "获取权限树形结构")
    @GetMapping("/getPermissionTreeList/{userId}")
    public WebResponse<List<PermissionTreeForLoginDTO>> getPermissionTreeList(@PathVariable("userId") Long userId) throws Exception {
        TokenUser user = SecurityUtils.getUser();
        List<PermissionTreeForLoginDTO> permissionTreeList = ucPermissionService.selectPermissionListByUserIdAndTenantId(userId, user.getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, permissionTreeList);
    }

    @ApiOperation(value = "获取全部权限树")
    @GetMapping("/getAllPermTree")
    public WebResponse<List<PermissionTreeForLoginDTO>> getAllPermTree(){
        List<PermissionTreeForLoginDTO> res  = ucPermissionService.getAllPermTree();
        return new WebResponse<>(UCResponseCode.SUCCESS,res);
    }

    @ApiOperation(value = "获取全部管理端权限树")
    @GetMapping("/getAllManagePermissionTree")
    public WebResponse<List<PermissionTreeForLoginDTO>> getAllManagePermissionTree(){
        List<PermissionTreeForLoginDTO> permissionTreeList = ucPermissionService.getAllManagePermissionTree();
        return new WebResponse<>(UCResponseCode.SUCCESS, permissionTreeList);
    }

    @ApiOperation(value = "获取全部租户端权限树")
    @GetMapping("/getAllTenantPermissionTree")
    public WebResponse<List<PermissionTreeForLoginDTO>> getAllTenantPermissionTree(){
        List<PermissionTreeForLoginDTO> permissionTreeList = ucPermissionService.getAllTenantPermissionTree();
        return new WebResponse<>(UCResponseCode.SUCCESS, permissionTreeList);
    }

}


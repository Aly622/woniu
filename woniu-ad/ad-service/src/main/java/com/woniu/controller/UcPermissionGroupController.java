package com.woniu.controller;


import com.woniu.entity.UcPermissionGroup;
import com.woniu.response.UCResponseCode;
import com.woniu.response.WebResponse;
import com.woniu.service.UcPermissionGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 权限组表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Api(tags = "权限组相关接口")
@RestController
@RequestMapping("/${spring.application.name}/v1/permissionGroup")
public class UcPermissionGroupController extends SuperController {

    @Autowired
    private UcPermissionGroupService permissionGroupService;

    @ApiOperation(value = "获取权限组列表")
    @GetMapping("/getPermGroupList")
    public WebResponse<List<UcPermissionGroup>> getPermGroupList(){
        List<UcPermissionGroup> list = permissionGroupService.list();
        return new WebResponse<List<UcPermissionGroup>>(UCResponseCode.SUCCESS,list);
    }



}


package com.woniu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.esmartwave.niumeng.diap.dto.*;
import com.woniu.dto.*;
import com.woniu.enums.UserTypeEnum;
import com.esmartwave.niumeng.diap.extend.TokenUser;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcUserService;
import com.woniu.utils.SecurityUtils;
import com.esmartwave.niumeng.diap.vo.*;
import com.woniu.vo.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@RestController
@RequestMapping("/${spring.application.name}/v1/user")
public class UcUserController extends SuperController {

    @Autowired
    private UcUserService ucUserService;

    @ApiOperation(value = "分页查询账户列表（管理端）")
    @PostMapping("/getUserPageForManage")
    public WebResponse<Page<UserPageForManageResultDTO>> getUserPageForManage(@RequestBody UserPageQueryForManageVO userPageQueryForManage) throws Exception {
        log.info("#### 分页查询账户列表（管理端）入参：{}", ToStringBuilder.reflectionToString(userPageQueryForManage));
        Page<UserPageForManageResultDTO> page = ucUserService.selectUserPageForManage(userPageQueryForManage);
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "创建账户（管理端）")
    @PostMapping("/createUserForManage")
    public WebResponse createUserForManage(@RequestBody @Valid CreateUserForManageVO createUserForManage) throws Exception {
        log.info("#### 创建账户（管理端）入参：{}", ToStringBuilder.reflectionToString(createUserForManage));
        if(!match("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*$", createUserForManage.getEmail())) {
            return new WebResponse(UCResponseCode.EMAIL_PATTERN_IS_NOT_CORRECT);
        }
        if(!match("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", createUserForManage.getMobile())) {
            return new WebResponse(UCResponseCode.MOBILE_PATTERN_IS_NOT_CORRECT);
        }
        if(createUserForManage.getType() != UserTypeEnum.USER_TYPE_PLATFORM.getCode() && createUserForManage.getType() != UserTypeEnum.USER_TYPE_TENANT.getCode()) {
            return new WebResponse(UCResponseCode.USER_TYPE_IS_ERROR);
        }
        if(createUserForManage.getType() == UserTypeEnum.USER_TYPE_PLATFORM.getCode()) {
            if(CollectionUtils.isNotEmpty(createUserForManage.getUserBindTenantList())) {
                return new WebResponse(UCResponseCode.USER_BIND_TENANT_LIST_HAS_DATA);
            }
        }
        if(createUserForManage.getType() == UserTypeEnum.USER_TYPE_TENANT.getCode()) {
            if(CollectionUtils.isEmpty(createUserForManage.getUserBindTenantList())) {
                return new WebResponse(UCResponseCode.USER_BIND_TENANT_LIST_NO_DATA);
            }
        }
        Boolean createFlag = ucUserService.createUserForManage(createUserForManage);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "查询账户详情（管理端）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="账户ID",required=true),
    })
    @GetMapping("/getUserInfo/{id}")
    public WebResponse<UserInfoForManageDTO> getUserInfo(@PathVariable("id") Long id) throws Exception {
        log.info("#### 查询账户详情（管理端）入参：id = {}", id);
        UserInfoForManageDTO userInfoForManage = ucUserService.selectUserInfo(id);
        return new WebResponse(UCResponseCode.SUCCESS, userInfoForManage);
    }

    @ApiOperation(value = "修改账户（管理端）")
    @PostMapping("/updateUserForManage")
    public WebResponse updateUserForManage(@RequestBody @Valid UpdateUserForManageVO updateUserForManage) throws Exception {
        log.info("#### 修改账户（管理端）入参：{}", ToStringBuilder.reflectionToString(updateUserForManage));
        if(!match("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*$", updateUserForManage.getEmail())) {
            return new WebResponse(UCResponseCode.EMAIL_PATTERN_IS_NOT_CORRECT);
        }
        if(!match("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", updateUserForManage.getMobile())) {
            return new WebResponse(UCResponseCode.MOBILE_PATTERN_IS_NOT_CORRECT);
        }
        if(updateUserForManage.getType() != UserTypeEnum.USER_TYPE_PLATFORM.getCode() && updateUserForManage.getType() != UserTypeEnum.USER_TYPE_TENANT.getCode()) {
            return new WebResponse(UCResponseCode.USER_TYPE_IS_ERROR);
        }
        if(updateUserForManage.getType() == UserTypeEnum.USER_TYPE_PLATFORM.getCode()) {
            if(CollectionUtils.isNotEmpty(updateUserForManage.getUserBindTenantList())) {
                return new WebResponse(UCResponseCode.USER_BIND_TENANT_LIST_HAS_DATA);
            }
        }
        if(updateUserForManage.getType() == UserTypeEnum.USER_TYPE_TENANT.getCode()) {
            if(CollectionUtils.isEmpty(updateUserForManage.getUserBindTenantList())) {
                return new WebResponse(UCResponseCode.USER_BIND_TENANT_LIST_NO_DATA);
            }
        }
        Boolean updateFlag = ucUserService.updateUserForManage(updateUserForManage);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "创建或更新账户和角色关系(管理端和租户端公用)")
    @PostMapping("/createOrUpdateUserRoleRelation")
    public WebResponse createOrUpdateUserRoleRelation(@RequestBody @Valid CreateOrUpdateUserRoleRelationVO createOrUpdateUserRoleRelation) throws Exception {
        log.info("#### 创建或更新账户和角色关系(管理端和租户端公用)入参：{}", ToStringBuilder.reflectionToString(createOrUpdateUserRoleRelation));
        Boolean createOrUpdateFlag = ucUserService.createOrUpdateUserRoleRelation(createOrUpdateUserRoleRelation);
        if(createOrUpdateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "分页查询账户列表（租户端）")
    @PostMapping("/getUserPageForTenant")
    public WebResponse<Page<UserPageForTenantResultDTO>> getUserPageForTenant(@RequestBody UserPageQueryForTenantVO userPageQueryForTenant) throws Exception {
        log.info("#### 分页查询账户列表（租户端）入参：{}", ToStringBuilder.reflectionToString(userPageQueryForTenant));
        if(userPageQueryForTenant.getType() == null) {
            return new WebResponse(UCResponseCode.USER_TYPE_IS_NULL);
        }
        TokenUser user = SecurityUtils.getUser();
        Page<UserPageForTenantResultDTO> page = ucUserService.selectUserPageForTenant(userPageQueryForTenant, user.getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, page);
    }

    @ApiOperation(value = "创建账户（租户端）")
    @PostMapping("/createUserForTenant")
    public WebResponse createUserForTenant(@RequestBody @Valid CreateUserForTenantVO createUserForTenant) throws Exception {
        log.info("#### 创建账户（租户端）入参：{}", ToStringBuilder.reflectionToString(createUserForTenant));
        if(!match("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*$", createUserForTenant.getEmail())) {
            return new WebResponse(UCResponseCode.EMAIL_PATTERN_IS_NOT_CORRECT);
        }
        if(!match("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", createUserForTenant.getMobile())) {
            return new WebResponse(UCResponseCode.MOBILE_PATTERN_IS_NOT_CORRECT);
        }
        if(createUserForTenant.getType() != UserTypeEnum.USER_TYPE_TENANT.getCode() && createUserForTenant.getType() != UserTypeEnum.USER_TYPE_MANUFACTURER.getCode()) {
            return new WebResponse(UCResponseCode.USER_TYPE_IS_ERROR);
        }
        Boolean createFlag = ucUserService.createUserForTenant(createUserForTenant);
        if(createFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "查询账户详情（租户端）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="账户ID",required=true),
    })
    @GetMapping("/getUserInfoForTenant/{id}")
    public WebResponse<UserInfoForTenantDTO> getUserInfoForTenant(@PathVariable("id") Long id) throws Exception {
        log.info("#### 查询账户详情（租户端）入参：id = {}", id);
        TokenUser user = SecurityUtils.getUser();
        UserInfoForTenantDTO userInfoForTenant = ucUserService.selectUserInfoForTenant(id, user.getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, userInfoForTenant);
    }

    @ApiOperation(value = "修改账户（租户端）")
    @PostMapping("/updateUserForTenant")
    public WebResponse updateUserForTenant(@RequestBody @Valid UpdateUserForTenantVO updateUserForTenant) throws Exception {
        log.info("#### 修改账户（租户端）入参：{}", ToStringBuilder.reflectionToString(updateUserForTenant));
        if(!match("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*$", updateUserForTenant.getEmail())) {
            return new WebResponse(UCResponseCode.EMAIL_PATTERN_IS_NOT_CORRECT);
        }
        if(!match("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", updateUserForTenant.getMobile())) {
            return new WebResponse(UCResponseCode.MOBILE_PATTERN_IS_NOT_CORRECT);
        }
        if(updateUserForTenant.getType() != UserTypeEnum.USER_TYPE_TENANT.getCode() && updateUserForTenant.getType() != UserTypeEnum.USER_TYPE_MANUFACTURER.getCode()) {
            return new WebResponse(UCResponseCode.USER_TYPE_IS_ERROR);
        }
        Boolean updateFlag = ucUserService.updateUserForTenant(updateUserForTenant);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "根据账户ID查询已绑定的角色列表（管理端和租户端公用）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="账户ID",required=true),
    })
    @GetMapping("/getRoleListByUserId/{id}")
    public WebResponse<List<RoleInfoDTO>> getRoleListByUserId(@PathVariable("id") Long id) throws Exception {
        log.info("#### 根据账户ID查询已绑定的角色列表（管理端和租户端公用）入参：id = {}", id);
        List<RoleInfoDTO> roleInfoList = ucUserService.selectRoleInfoByUserId(id, SecurityUtils.getUser().getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, roleInfoList);
    }

    @ApiOperation(value = "发送邮箱验证码（管理端和租户端公用）")
    @PostMapping("/sendEmailVerificationCode")
    public WebResponse<Boolean> sendEmailVerificationCode(@RequestBody SendEmailVerificationCodeVO sendEmailVerificationCode) throws Exception {
        log.info("#### 发送邮箱验证码（管理端和租户端公用）入参：{}", ToStringBuilder.reflectionToString(sendEmailVerificationCode));
        Boolean sendFlag = ucUserService.sendEmailVerificationCode(sendEmailVerificationCode.getEmail());
        if(sendFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "根据邮箱更新密码（管理端和租户端公用）")
    @PostMapping("/updatePasswordByEmail")
    public WebResponse updatePasswordByEmail(@RequestBody UpdatePasswordWithEmailVO updatePasswordWithEmail) throws Exception {
        log.info("#### 根据邮箱更新密码（管理端和租户端公用）入参：{}", ToStringBuilder.reflectionToString(updatePasswordWithEmail));
        Boolean updateFlag = ucUserService.updatePasswordByEmail(updatePasswordWithEmail);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "根据账户ID更新密码（管理端和租户端公用）")
    @PostMapping("/updatePasswordById")
    public WebResponse updatePasswordById(@RequestBody UpdatePasswordVO updatePassword) throws Exception {
        log.info("#### 根据账户ID更新密码（管理端和租户端公用）入参：{}", ToStringBuilder.reflectionToString(updatePassword));
        Boolean updateFlag = ucUserService.updatePasswordById(updatePassword);
        if(updateFlag) {
            return new WebResponse(UCResponseCode.SUCCESS);
        } else {
            return new WebResponse(UCResponseCode.FAIL);
        }
    }

    @ApiOperation(value = "查询所有已启用的角色（排除已授权的角色）（租户端和管理端公用）")
    @PostMapping("/selectNotAuthRoleList")
    public WebResponse<List<RoleInfoDTO>> selectNotAuthRoleList(@RequestBody UserIdAndRoleNameQueryVO userIdAndRoleNameQuery) throws Exception {
        log.info("#### 查询所有已启用的角色（排除已授权的角色）（租户端和管理端公用）参数：{}", ToStringBuilder.reflectionToString(userIdAndRoleNameQuery));
        List<RoleInfoDTO> roleInfoList = ucUserService.selectNotAuthRoleList(userIdAndRoleNameQuery.getRoleName(), userIdAndRoleNameQuery.getUserId(), SecurityUtils.getUser().getTenantId());
        return new WebResponse(UCResponseCode.SUCCESS, roleInfoList);
    }

    private static Boolean match(String rex, String str) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}


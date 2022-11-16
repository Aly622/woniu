package com.woniu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.esmartwave.niumeng.diap.dto.*;
import com.woniu.dto.*;
import com.woniu.entity.UcUser;
import com.esmartwave.niumeng.diap.vo.*;
import com.woniu.vo.*;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UcUserService extends IService<UcUser> {

    /**
     * @param userPageQueryForManage
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.UserPageForManageResultDTO>
     * @desc: 分页查询账户列表（管理端）
     * @date: 2022/3/3 10:49
     */
    Page<UserPageForManageResultDTO> selectUserPageForManage(UserPageQueryForManageVO userPageQueryForManage);

    /**
     * @param createUserForManage
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建账户（管理端）
     * @date: 2022/3/3 11:26
     */
    Boolean createUserForManage(CreateUserForManageVO createUserForManage);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.UserInfoForManageDTO
     * @desc: 查询账户详情
     * @date: 2022/3/3 16:20
     */
    UserInfoForManageDTO selectUserInfo(Long id);

    /**
     * @param updateUserForManage
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 更新用户
     * @date: 2022/3/4 11:22
     */
    Boolean updateUserForManage(UpdateUserForManageVO updateUserForManage);

    /**
     * @param createOrUpdateUserRoleRelation
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建或更新账户和角色关系(管理端)
     * @date: 2022/4/11 15:46
     */
    Boolean createOrUpdateUserRoleRelation(CreateOrUpdateUserRoleRelationVO createOrUpdateUserRoleRelation);

    /**
     * @param userPageQueryForTenant
     * @param tenantId
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.UserPageForTenantResultDTO>
     * @desc: 分页查询账户列表（租户端）
     * @date: 2022/3/7 13:38
     */
    Page<UserPageForTenantResultDTO> selectUserPageForTenant(UserPageQueryForTenantVO userPageQueryForTenant, Long tenantId);
    
    /**
     * @param createUserForTenant
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 创建账户（租户端）
     * @date: 2022/3/7 17:04
     */
    Boolean createUserForTenant(CreateUserForTenantVO createUserForTenant);

    /**
     * @param id
     * @param tenantId
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.UserInfoForTenantDTO
     * @desc: 查询账户详情（租户端）
     * @date: 2022/3/8 11:25
     */
    UserInfoForTenantDTO selectUserInfoForTenant(Long id, Long tenantId);

    /**
     * @param updateUserForTenant
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 更新账户（租户端）
     * @date: 2022/3/8 11:42
     */
    Boolean updateUserForTenant(UpdateUserForTenantVO updateUserForTenant);

    /**
     * @param userName
     * @param password
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.entity.UcUser
     * @desc: 根据用户名密码获取账户信息
     * @date: 2022/3/9 15:27
     */
    UcUser getUcUserByUserNameAndPassword(String userName, String password);

    /**
     * @param mobile
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.entity.UcUser
     * @desc: 根据手机号查询账户信息
     * @date: 2022/4/25 12:09
     */
    UcUser getUcUserByMobile(String mobile);

    /**
     * @param id
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 根据账户ID查询已绑定的角色列表
     * @date: 2022/3/17 10:59
     */
    List<RoleInfoDTO> selectRoleInfoByUserId(Long id, Long tenantId);

    /**
     * @param email
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 发送邮箱验证码
     * @date: 2022/3/29 14:34
     */
    Boolean sendEmailVerificationCode(String email);

    /**
     * @param updatePasswordWithEmail
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 根据邮箱更新密码
     * @date: 2022/3/29 14:53
     */
    Boolean updatePasswordByEmail(UpdatePasswordWithEmailVO updatePasswordWithEmail);

    /**
     * @param updatePassword
     * @author: mike.ma
     * @return: java.lang.Boolean
     * @desc: 根据账户ID更新密码
     * @date: 2022/3/29 15:17
     */
    Boolean updatePasswordById(UpdatePasswordVO updatePassword);

    /**
     * @param roleName
     * @param userId
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 根据账户ID和角色名称查询未授权的角色列表
     * @date: 2022/4/11 18:54
     */
    List<RoleInfoDTO> selectNotAuthRoleList(String roleName, Long userId, Long tenantId);

}

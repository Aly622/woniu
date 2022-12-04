package com.woniu.dao.custom;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.dao.SuperMapper;
import com.woniu.dto.*;
import com.woniu.entity.UcUser;
import com.woniu.vo.UpdatePasswordWithEmailVO;
import com.woniu.vo.UserPageQueryForManageVO;
import com.woniu.vo.UserPageQueryForTenantVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
public interface UserMapper extends SuperMapper<UcUser> {

    /**
     * @param userPageQueryForManage
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.UserPageForManageResultDTO>
     * @desc: 分页查询账户列表（管理端）
     * @date: 2022/3/3 10:49
     */
    @InterceptorIgnore(tenantLine = "true")
    Page<UserPageForManageResultDTO> selectUserPageForManage(UserPageQueryForManageVO userPageQueryForManage);

    /**
     * @param id
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.UserInfoForManageDTO
     * @desc: 查询账户详情
     * @date: 2022/3/3 16:20
     */
    @InterceptorIgnore(tenantLine = "true")
    UserInfoForManageDTO selectUserInfo(@Param("id") Long id);

    /**
     * @param userPageQueryForTenant
     * @author: mike.ma
     * @return: com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.esmartwave.niumeng.diap.dto.UserPageForTenantResultDTO>
     * @desc: 分页查询账户列表（租户端）
     * @date: 2022/3/7 13:36
     */
    @InterceptorIgnore(tenantLine = "true")
    Page<UserPageForTenantResultDTO> selectUserPageForTenant(UserPageQueryForTenantVO userPageQueryForTenant);

    /**
     * @param id
     * @param tenantId
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.UserInfoForTenantDTO
     * @desc: 查询账户详情（租户端）
     * @date: 2022/3/8 11:25
     */
    @InterceptorIgnore(tenantLine = "true")
    UserInfoForTenantDTO selectUserInfoForTenant(@Param("id") Long id, @Param("tenantId") Long tenantId);

    /**
     * @param id
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<com.esmartwave.niumeng.diap.dto.RoleInfoDTO>
     * @desc: 根据账户ID查询已绑定的角色列表
     * @date: 2022/3/17 10:59
     */
    @InterceptorIgnore(tenantLine = "true")
    List<RoleInfoDTO> selectRoleInfoByUserId(@Param("id") Long id, @Param("tenantId") Long tenantId);

    /**
     * @param email
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据邮箱查询账户数量
     * @date: 2022/3/29 14:37
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByEmail(@Param("email") String email);

    /**
     * @param mobile
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据手机号查询账户数量
     * @date: 2022/4/25 14:13
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByMobile(@Param("mobile") String mobile);

    /**
     * @param email
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.UserNameAndNickNameDTO
     * @desc: 根据邮箱查询账户nickName和userName
     * @date: 2022/4/22 17:33
     */
    @InterceptorIgnore(tenantLine = "true")
    UserNameAndNickNameDTO selectNickNameByEmail(@Param("email") String email);

    /**
     * @param updatePasswordWithEmail
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据邮箱更新密码
     * @date: 2022/3/29 14:56
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer updatePasswordByEmail(UpdatePasswordWithEmailVO updatePasswordWithEmail);

    /**
     * @param id
     * @param oldPassword
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和密码查询用户数量
     * @date: 2022/3/29 15:22
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectPasswordById(@Param("id") Long id, @Param("oldPassword") String oldPassword);

    /**
     * @param id
     * @param newPassword
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID更新密码
     * @date: 2022/3/29 15:28
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer updatePasswordById(@Param("id") Long id, @Param("newPassword") String newPassword);

    /**
     * @param id
     * @param email
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和邮箱查询账户数量
     * @date: 2022/3/29 15:49
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByIdAndEmail(@Param("id") Long id, @Param("email") String email);

    /**
     * @param id
     * @param mobile
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和手机号查询账户数量
     * @date: 2022/3/29 15:49
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByIdAndMobile(@Param("id") Long id, @Param("mobile") String mobile);

    /**
     * @param userName
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户昵称查询账户数量
     * @date: 2022/3/31 16:31
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByUserName(@Param("userName") String userName);

    /**
     * @param userName
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID和账户昵称查询账户数量
     * @date: 2022/3/31 16:31
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserCountByIdAndUserName(@Param("id") Long id, @Param("userName") String userName);

    /**
     * @param id
     * @param tenantId
     * @author: mike.ma
     * @return: java.util.List<java.lang.Long>
     * @desc: 根据账户ID查询已绑定的角色ID列表
     * @date: 2022/4/11 18:16
     */
    @InterceptorIgnore(tenantLine = "true")
    List<Long> selectRoleIdListByUserId(@Param("id") Long id, @Param("tenantId") Long tenantId);

    /**
     * @param id
     * @author: mike.ma
     * @return: java.lang.Integer
     * @desc: 根据账户ID查询账户类型
     * @date: 2022/5/7 10:57
     */
    @InterceptorIgnore(tenantLine = "true")
    Integer selectUserTypeByUserId(@Param("id") Long id);
}

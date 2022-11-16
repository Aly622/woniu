package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.woniu.client.UcMessageCenterClient;
import com.esmartwave.niumeng.diap.client.VendTableClient;
import com.esmartwave.niumeng.diap.dao.custom.*;
import com.esmartwave.niumeng.diap.dto.*;
import com.esmartwave.niumeng.diap.entity.*;
import com.woniu.dao.UcUserMapper;
import com.woniu.dao.custom.*;
import com.woniu.dto.*;
import com.woniu.entity.*;
import com.woniu.enums.EmailTemplateTypeEnum;
import com.woniu.enums.IsAdminEnum;
import com.woniu.enums.UserStatusEnum;
import com.woniu.enums.UserTypeEnum;
import com.esmartwave.niumeng.diap.exception.ServiceException;
import com.esmartwave.niumeng.diap.response.IResponseCode;
import com.esmartwave.niumeng.diap.response.MessageCenterResponse;
import com.woniu.response.UCResponseCode;
import com.esmartwave.niumeng.diap.response.WebResponse;
import com.woniu.service.UcUserOrgRelationService;
import com.woniu.service.UcUserRoleRelationService;
import com.woniu.service.UcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniu.service.UcUserTenaRelationService;
import com.esmartwave.niumeng.diap.utils.MD5Utils;
import com.esmartwave.niumeng.diap.utils.ObjectCopier;
import com.esmartwave.niumeng.diap.vo.*;
import com.woniu.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Admin
 * @since 2022-02-24
 */
@Slf4j
@Service
public class UcUserServiceImpl extends ServiceImpl<UcUserMapper, UcUser> implements UcUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private VendTableClient vendTableClient;

    @Autowired
    private UserTenaRelationMapper userTenaRelationMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Autowired
    private UserOrgRelationMapper userOrgRelationMapper;

    @Autowired
    private UserCampaRelationMapper userCampaRelationMapper;

    @Autowired
    private OrgDetailMapper orgDetailMapper;

    @Autowired
    private UcUserTenaRelationService ucUserTenaRelationService;

    @Autowired
    private UcUserRoleRelationService ucUserRoleRelationService;

    @Autowired
    private UcUserOrgRelationService ucUserOrgRelationService;

    @Autowired
    private UcMessageCenterClient ucMessageCenterClient;

    @Override
    public Page<UserPageForManageResultDTO> selectUserPageForManage(UserPageQueryForManageVO userPageQueryForManage) {
        return userMapper.selectUserPageForManage(userPageQueryForManage);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED)
    @Override
    public Boolean createUserForManage(CreateUserForManageVO createUserForManage) {
        Integer userCountByUserName = userMapper.selectUserCountByUserName(createUserForManage.getUserName());
        if(userCountByUserName > 0) {
            throw new ServiceException(UCResponseCode.USER_NAME_IS_EXIST);
        }
        Integer userCountByMobile = userMapper.selectUserCountByMobile(createUserForManage.getMobile());
        if(userCountByMobile > 0) {
            throw new ServiceException(UCResponseCode.MOBILE_IS_EXISTS);
        }
        Integer userCountByEmail = userMapper.selectUserCountByEmail(createUserForManage.getEmail());
        if(userCountByEmail > 0) {
            throw new ServiceException(UCResponseCode.EMAIL_IS_EXIST);
        }
        createUserForManage.setPassword(MD5Utils.stringToMD5(createUserForManage.getPassword()));
        if(CollectionUtils.isNotEmpty(createUserForManage.getUserBindTenantList())) {
            //校验租户绑定列表中是否存在重复租户
            validateTenantRepeat(createUserForManage.getUserBindTenantList());
        }
        UcUser ucUser = ObjectCopier.copyObject(createUserForManage, UcUser.class);
        save(ucUser);

        List<UserBindTenantVO> userBindTenantList = createUserForManage.getUserBindTenantList();
        //创建账户与租户、组织、admin角色的绑定关系
        saveUserTenantOrgRoleRelation(ucUser, userBindTenantList);
        return true;
    }

    @Override
    public UserInfoForManageDTO selectUserInfo(Long id) {
        return userMapper.selectUserInfo(id);
    }

    @Transactional(isolation= Isolation.READ_COMMITTED)
    @Override
    public Boolean updateUserForManage(UpdateUserForManageVO updateUserForManage) {
        Integer userCountByIdAndUserName = userMapper.selectUserCountByIdAndUserName(updateUserForManage.getId(), updateUserForManage.getUserName());
        if(userCountByIdAndUserName > 0) {
            throw new ServiceException(UCResponseCode.USER_NAME_IS_EXIST);
        }
        Integer userCountByIdAndMobile = userMapper.selectUserCountByIdAndMobile(updateUserForManage.getId(), updateUserForManage.getMobile());
        if(userCountByIdAndMobile > 0) {
            throw new ServiceException(UCResponseCode.MOBILE_IS_EXISTS);
        }
        Integer userCountByIdAndEmail = userMapper.selectUserCountByIdAndEmail(updateUserForManage.getId(), updateUserForManage.getEmail());
        if(userCountByIdAndEmail > 0) {
            throw new ServiceException(UCResponseCode.EMAIL_IS_EXIST);
        }
        if(CollectionUtils.isNotEmpty(updateUserForManage.getUserBindTenantList())) {
            //校验租户绑定列表中是否存在重复租户
            validateTenantRepeat(updateUserForManage.getUserBindTenantList());
        }
        Integer type = userMapper.selectUserTypeByUserId(updateUserForManage.getId());
        UcUser ucUser = ObjectCopier.copyObject(updateUserForManage, UcUser.class);
        updateById(ucUser);

        if(updateUserForManage.getType() != UserTypeEnum.USER_TYPE_PLATFORM.getCode() || type != UserTypeEnum.USER_TYPE_PLATFORM.getCode()) {
            List<UserBindTenantVO> userBindTenantVOList = updateUserForManage.getUserBindTenantList();
            List<UserBindTenantDTO> userBindTenantDTOList = userTenaRelationMapper.selectAllTenant(updateUserForManage.getId());
            if(CollectionUtils.isEmpty(userBindTenantVOList)) {
                //删除当前账户与租户、组织、角色、活动的绑定关系
                deleteUserTenantRoleOrgCampaRelation(updateUserForManage.getId(), userBindTenantDTOList);
                saveUserTenantRelation(updateUserForManage.getId(), null);
            } else {
                if(CollectionUtils.isNotEmpty(userBindTenantDTOList)) {
                    //计算userBindTenantVOList和userBindTenantDTOList差集，返回List<UserBindTenantVO>多的内容用于创建关系
                    List<UserBindTenantVO> diffUserBindTenantVOList = userBindTenantVOList.stream().filter(userBindTenantVO ->
                            !userBindTenantDTOList.stream().map(userBindTenantDTO ->
                                    userBindTenantDTO.getTenantId()).collect(Collectors.toList())
                                    .contains(userBindTenantVO.getTenantId())).collect(Collectors.toList());

                    //计算userBindTenantDTOList和userBindTenantVOList差集，返回List<UserBindTenantDTO>多的内容用于删除关系
                    List<UserBindTenantDTO> diffUserBindTenantDTOList = userBindTenantDTOList.stream().filter(userBindTenantDTO ->
                            !userBindTenantVOList.stream().map(userBindTenantVO ->
                                    userBindTenantVO.getTenantId()).collect(Collectors.toList())
                                    .contains(userBindTenantDTO.getTenantId())).collect(Collectors.toList());

                    List<UserBindTenantVO> intersectionUserBindTenantVOList = userBindTenantVOList.stream().filter(userBindTenantVO ->
                            userBindTenantDTOList.stream().map(userBindTenantDTO ->
                                    userBindTenantDTO.getTenantId()).collect(Collectors.toList())
                                    .contains(userBindTenantVO.getTenantId())).collect(Collectors.toList());

                    //删除当前账户与租户、组织、角色、活动的绑定关系
                    deleteUserTenantRoleOrgCampaRelation(updateUserForManage.getId(), diffUserBindTenantDTOList);

                    if(CollectionUtils.isNotEmpty(diffUserBindTenantVOList)) {
                        //创建账户与租户、组织、admin角色的绑定关系
                        saveUserTenantOrgRoleRelation(ucUser, diffUserBindTenantVOList);
                    }

                    if(CollectionUtils.isNotEmpty(intersectionUserBindTenantVOList)) {
                        //更新admin账户与admin角色的绑定关系
                        updateAdminUserRoleRelation(ucUser.getId(), intersectionUserBindTenantVOList);
                        //更新账户与租户的绑定关系
                        updateUserTenantRelation(ucUser.getId(), intersectionUserBindTenantVOList);

                    }
                } else {
                    //创建账户与租户、组织、admin角色的绑定关系
                    saveUserTenantOrgRoleRelation(ucUser, userBindTenantVOList);
                }
            }
        }
        return true;
    }

    @Transactional
    @Override
    public Boolean createOrUpdateUserRoleRelation(CreateOrUpdateUserRoleRelationVO createOrUpdateUserRoleRelation) {
        QueryWrapper<UcUserRoleRelation> ucUserRoleRelationQueryWrapper = new QueryWrapper<>();
        ucUserRoleRelationQueryWrapper.eq("user_id", createOrUpdateUserRoleRelation.getUserId());
        ucUserRoleRelationService.remove(ucUserRoleRelationQueryWrapper);
        List<UcUserRoleRelation> ucUserRoleRelationList = new ArrayList<>();
        for(Long roleId : createOrUpdateUserRoleRelation.getRoleIdList()) {
            UcUserRoleRelation ucUserRoleRelation = new UcUserRoleRelation();
            ucUserRoleRelation.setUserId(createOrUpdateUserRoleRelation.getUserId());
            ucUserRoleRelation.setRoleId(roleId);
            ucUserRoleRelationList.add(ucUserRoleRelation);
        }
        if(CollectionUtils.isNotEmpty(ucUserRoleRelationList)) {
            ucUserRoleRelationService.saveBatch(ucUserRoleRelationList);
        }
        return true;
    }

    @Override
    public Page<UserPageForTenantResultDTO> selectUserPageForTenant(UserPageQueryForTenantVO userPageQueryForTenant, Long tenantId) {
        userPageQueryForTenant.setTenantId(tenantId);
        if(userPageQueryForTenant.getType() == UserTypeEnum.USER_TYPE_TENANT.getCode()) {
            if(userPageQueryForTenant.getOrgId() != null) {
                List<Long> childOrgLIdList = new ArrayList<>();
                List<UcOrg> ucOrgList = orgMapper.selectAbleOrgListForOrgTree();
                childOrgLIdList.add(userPageQueryForTenant.getOrgId());
                recursionGetChildOrgIdList(ucOrgList, userPageQueryForTenant.getOrgId(), childOrgLIdList);
                userPageQueryForTenant.setOrgIdList(childOrgLIdList);
            }
            return userMapper.selectUserPageForTenant(userPageQueryForTenant);
        } else if(userPageQueryForTenant.getType() == UserTypeEnum.USER_TYPE_MANUFACTURER.getCode()) {
            Page<UserPageForTenantResultDTO> page = userMapper.selectUserPageForTenant(userPageQueryForTenant);
            if(CollectionUtils.isNotEmpty(page.getRecords())) {
                List<Long> vendIdList = page.getRecords().stream().map(userInfo -> userInfo.getVendId()).collect(Collectors.toList());
                if(CollectionUtils.isNotEmpty(vendIdList)) {
                    List<VendInfoDTO> vendInfoList = getResponseResult(vendTableClient.getVendInfoListByVendIdList(new GetVendInfoListVO(vendIdList, tenantId)));
                    if(CollectionUtils.isNotEmpty(vendInfoList)) {
                        vendInfoList.stream().flatMap(vendInfo ->
                                page.getRecords().stream().filter(userInfo ->
                                        vendInfo.getId().equals(userInfo.getVendId())).map(userInfo -> {
                                            userInfo.setOrgName(vendInfo.getName());
                                            return userInfo;
                                        })).collect(Collectors.toList());
                    }
                }
            }
            return page;
        }
        return null;
    }

    private static List<Long> recursionGetChildOrgIdList(List<UcOrg> ucOrgList, Long id, List<Long>  childOrgLIdList) {
        for(UcOrg ucOrg : ucOrgList) {
            if(ucOrg.getParentId().equals(id)) {
                recursionGetChildOrgIdList(ucOrgList, ucOrg.getId(), childOrgLIdList);
                childOrgLIdList.add(ucOrg.getId());
            }
        }
        return childOrgLIdList;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Boolean createUserForTenant(CreateUserForTenantVO createUserForTenant) {
        Integer userCountByUserName = userMapper.selectUserCountByUserName(createUserForTenant.getUserName());
        if(userCountByUserName > 0) {
            throw new ServiceException(UCResponseCode.USER_NAME_IS_EXIST);
        }
        Integer userCountByMobile = userMapper.selectUserCountByMobile(createUserForTenant.getMobile());
        if(userCountByMobile > 0) {
            throw new ServiceException(UCResponseCode.MOBILE_IS_EXISTS);
        }
        Integer userCountByEmail = userMapper.selectUserCountByEmail(createUserForTenant.getEmail());
        if(userCountByEmail > 0) {
            throw new ServiceException(UCResponseCode.EMAIL_IS_EXIST);
        }
        createUserForTenant.setPassword(MD5Utils.stringToMD5(createUserForTenant.getPassword()));
        UcUser ucUser = ObjectCopier.copyObject(createUserForTenant, UcUser.class);
        save(ucUser);

        UcUserOrgRelation ucUserOrgRelation = new UcUserOrgRelation();
        ucUserOrgRelation.setUserId(ucUser.getId());
        ucUserOrgRelation.setOrgId(createUserForTenant.getOrgId());
        ucUserOrgRelationService.save(ucUserOrgRelation);

        UcUserTenaRelation ucUserTenaRelation = new UcUserTenaRelation();
        ucUserTenaRelation.setUserId(ucUser.getId());
        ucUserTenaRelation.setIsAdmin(IsAdminEnum.IS_ADMIN_NO.getCode());
        ucUserTenaRelationService.save(ucUserTenaRelation);

        return true;
    }

    @Override
    public UserInfoForTenantDTO selectUserInfoForTenant(Long id, Long tenantId) {
        return userMapper.selectUserInfoForTenant(id, tenantId);
    }

    @Transactional
    @Override
    public Boolean updateUserForTenant(UpdateUserForTenantVO updateUserForTenant) {
        Integer userCountByIdAndUserName = userMapper.selectUserCountByIdAndUserName(updateUserForTenant.getId(), updateUserForTenant.getUserName());
        if(userCountByIdAndUserName > 0) {
            throw new ServiceException(UCResponseCode.USER_NAME_IS_EXIST);
        }
        Integer userCountByIdAndMobile = userMapper.selectUserCountByIdAndMobile(updateUserForTenant.getId(), updateUserForTenant.getMobile());
        if(userCountByIdAndMobile > 0) {
            throw new ServiceException(UCResponseCode.MOBILE_IS_EXISTS);
        }
        Integer userCountByIdAndEmail = userMapper.selectUserCountByIdAndEmail(updateUserForTenant.getId(), updateUserForTenant.getEmail());
        if(userCountByIdAndEmail > 0) {
            throw new ServiceException(UCResponseCode.EMAIL_IS_EXIST);
        }
        UcUser ucUser = ObjectCopier.copyObject(updateUserForTenant, UcUser.class);
        updateById(ucUser);

        QueryWrapper<UcUserOrgRelation> ucUserOrgRelationQueryWrapper = new QueryWrapper<>();
        ucUserOrgRelationQueryWrapper.eq("user_id", ucUser.getId());
        ucUserOrgRelationService.remove(ucUserOrgRelationQueryWrapper);

        UcUserOrgRelation ucUserOrgRelation = new UcUserOrgRelation();
        ucUserOrgRelation.setUserId(ucUser.getId());
        ucUserOrgRelation.setOrgId(updateUserForTenant.getOrgId());
        ucUserOrgRelationService.save(ucUserOrgRelation);

        return true;
    }

    @Override
    public UcUser getUcUserByUserNameAndPassword(String userName, String password) {
        QueryWrapper<UcUser> ucUserQueryWrapper = new QueryWrapper<>();
        ucUserQueryWrapper.eq("user_name", userName);
        ucUserQueryWrapper.eq("password", password);
        ucUserQueryWrapper.eq("status", UserStatusEnum.STATUS_ABLE.getCode());
        return getOne(ucUserQueryWrapper);
    }

    @Override
    public UcUser getUcUserByMobile(String mobile) {
        QueryWrapper<UcUser> ucUserQueryWrapper = new QueryWrapper<>();
        ucUserQueryWrapper.eq("mobile", mobile);
        ucUserQueryWrapper.eq("status", UserStatusEnum.STATUS_ABLE.getCode());
        return getOne(ucUserQueryWrapper);
    }

    @Override
    public List<RoleInfoDTO> selectRoleInfoByUserId(Long id, Long tenantId) {
        return userMapper.selectRoleInfoByUserId(id, tenantId);
    }

    @Override
    public Boolean sendEmailVerificationCode(String email) {
        UserNameAndNickNameDTO userNameAndNickName = userMapper.selectNickNameByEmail(email);
        if(userNameAndNickName == null || StringUtils.isBlank(userNameAndNickName.getNickName()) || StringUtils.isBlank(userNameAndNickName.getUserName())) {
            throw new ServiceException(UCResponseCode.EMAIL_IS_NOT_EXIST);
        }

        Map<String, String> keyValuePairs = new HashMap<>();
        keyValuePairs.put("USERNICK", userNameAndNickName.getNickName());
        keyValuePairs.put("USERNAME", userNameAndNickName.getUserName());
        SendEmailVO sendEmail = new SendEmailVO(email, EmailTemplateTypeEnum.FORGET_PASSWORD.getCode(), keyValuePairs);
        log.info("#### 发送邮箱验证码参数：{}", JSON.toJSONString(sendEmail));
        MessageCenterResponse<Boolean> response = ucMessageCenterClient.sendEmail(sendEmail);
        log.info("#### 发送邮箱验证码返回值：{}", JSON.toJSONString(response));
        if(response != null && response.getIsSuccess() != null && response.getIsSuccess()) {
            return true;
        } else {
            throw new ServiceException(new IResponseCode() {
                public int getCode() {
                    return response.getCode();
                }
                public String getMessage() {
                    return response.getMessage();
                }
            });
        }
    }

    @Override
    public Boolean updatePasswordByEmail(UpdatePasswordWithEmailVO updatePasswordWithEmail) {
        //校验邮箱验证码
        log.info("#### 校验邮箱验证码参数：{}", JSON.toJSONString(updatePasswordWithEmail));
        MessageCenterResponse<Boolean> response = ucMessageCenterClient.validateAuthCode(new ValidateAuthCodeVO(updatePasswordWithEmail.getEmail(), updatePasswordWithEmail.getVerificationCode()));
        log.info("#### 校验邮箱验证码返回值：{}", JSON.toJSONString(response));
        if(response == null || response.getIsSuccess() == null || !response.getIsSuccess()) {
            throw new ServiceException(new IResponseCode() {
                public int getCode() {
                    return response.getCode();
                }
                public String getMessage() {
                    return response.getMessage();
                }
            });
        }
        updatePasswordWithEmail.setPassword(MD5Utils.stringToMD5(updatePasswordWithEmail.getPassword()));
        userMapper.updatePasswordByEmail(updatePasswordWithEmail);
        return true;
    }

    @Override
    public Boolean updatePasswordById(UpdatePasswordVO updatePassword) {
        Integer userCount = userMapper.selectPasswordById(updatePassword.getUserId(), MD5Utils.stringToMD5(updatePassword.getOldPassword()));
        if(userCount == 0) {
            throw new ServiceException(UCResponseCode.OLD_PASSWORD_IS_ERROR);
        }
        userMapper.updatePasswordById(updatePassword.getUserId(), MD5Utils.stringToMD5(updatePassword.getNewPassword()));
        return true;
    }

    @Override
    public List<RoleInfoDTO> selectNotAuthRoleList(String roleName, Long userId, Long tenantId) {
        List<RoleInfoDTO> roleInfoList;
        //根据账户ID查询已授权的角色ID列表
        List<Long> alreadyAuthRoleIdList = userMapper.selectRoleIdListByUserId(userId, tenantId);
        //如果已授权的角色ID列表不为空，则做排除，否则根据角色名称查询所有角色
        if(CollectionUtils.isNotEmpty(alreadyAuthRoleIdList)) {
            roleInfoList = roleMapper.selectNotAuthRoleList(alreadyAuthRoleIdList, roleName);
        } else {
            roleInfoList = roleMapper.selectAllAbleRole(new RoleQueryVO(roleName));
        }
        return roleInfoList;
    }

    /**
     * @param userId
     * @param userBindTenantDTOList
     * @author: mike.ma
     * @return: void
     * @desc: 删除当前账户与租户、组织、角色、活动的绑定关系
     * @date: 2022/3/4 17:05
     */
    private void deleteUserTenantRoleOrgCampaRelation(Long userId, List<UserBindTenantDTO> userBindTenantDTOList) {
        if(CollectionUtils.isNotEmpty(userBindTenantDTOList)) {
            List<Long> tenantIdList = userBindTenantDTOList.stream().map(userBindTenant -> userBindTenant.getTenantId()).collect(Collectors.toList());
            //根据账户ID删除所有账户与租户的绑定关系
            userTenaRelationMapper.deleteUserTenantRelationByUserId(userId, tenantIdList);
            //根据账户ID和租户ID列表删除账户和角色关系
            userRoleRelationMapper.deleteUserRoleRelationByUserId(userId, tenantIdList);
            //根据账户ID和租户ID列表删除账户和组织关系
            userOrgRelationMapper.deleteUserOrgRelationByUserId(userId, tenantIdList);
            //根据账户ID和租户ID列表删除账户和活动关系
            userCampaRelationMapper.deleteUserCampRelationByUserId(userId, tenantIdList);
        }
    }

    /**
     * @param ucUser
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 创建账户与租户、组织、admin角色的绑定关系
     * @date: 2022/3/4 17:02
     */
    private void saveUserTenantOrgRoleRelation(UcUser ucUser, List<UserBindTenantVO> userBindTenantList) {
        //创建账户与租户的绑定关系
        saveUserTenantRelation(ucUser.getId(), userBindTenantList);
        //创建账户与根组织的绑定关系
        saveUserRootOrgRelation(ucUser.getId(), userBindTenantList);
        //创建admin账户与admin角色的绑定关系
        saveAdminUserRoleRelation(ucUser.getId(), userBindTenantList);
    }

    /**
     * @param userId
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 创建账户与租户的绑定关系
     * @date: 2022/3/4 14:56
     */
    private void saveUserTenantRelation(Long userId, List<UserBindTenantVO> userBindTenantList) {
        if(CollectionUtils.isNotEmpty(userBindTenantList)) {
            List<UcUserTenaRelation> ucUserTenaRelationList = new ArrayList<>();
            for(UserBindTenantVO userBindTenant : userBindTenantList) {
                UcUserTenaRelation ucUserTenaRelation = new UcUserTenaRelation();
                ucUserTenaRelation.setUserId(userId);
                ucUserTenaRelation.setIsAdmin(userBindTenant.getIsAdmin());
                ucUserTenaRelation.setTenantId(userBindTenant.getTenantId());
                ucUserTenaRelationList.add(ucUserTenaRelation);
            }
            ucUserTenaRelationService.saveBatch(ucUserTenaRelationList);
        } else {
            UcUserTenaRelation ucUserTenaRelation = new UcUserTenaRelation();
            ucUserTenaRelation.setUserId(userId);
            ucUserTenaRelation.setIsAdmin(IsAdminEnum.IS_ADMIN_NO.getCode());
            ucUserTenaRelationService.save(ucUserTenaRelation);
        }
    }

    /**
     * @param userId
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 创建账户与根组织的绑定关系
     * @date: 2022/3/4 11:21
     */
    private void saveUserRootOrgRelation(Long userId, List<UserBindTenantVO> userBindTenantList) {
        if(CollectionUtils.isNotEmpty(userBindTenantList)) {
            List<UcUserOrgRelation> ucUserOrgRelationList = new ArrayList<>();
            List<BindUserOrgDTO> bindUserOrgList = orgMapper.selectRootOrgIdByTenantId(userBindTenantList.stream().map(userBindTenant -> userBindTenant.getTenantId()).collect(Collectors.toList()));
            if(CollectionUtils.isEmpty(bindUserOrgList)) {
                throw new ServiceException(UCResponseCode.TENANT_NOT_HAVE_ROOT_ORG);
            }
            for(BindUserOrgDTO bindUserOrg : bindUserOrgList) {
                UcUserOrgRelation ucUserOrgRelation = new UcUserOrgRelation();
                ucUserOrgRelation.setOrgId(bindUserOrg.getOrgId());
                ucUserOrgRelation.setUserId(userId);
                ucUserOrgRelation.setTenantId(bindUserOrg.getTenantId());
                ucUserOrgRelationList.add(ucUserOrgRelation);
            }
            if(CollectionUtils.isNotEmpty(ucUserOrgRelationList)) {
                ucUserOrgRelationService.saveBatch(ucUserOrgRelationList);
            }
        }
    }

    /**
     * @param userId
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 创建admin账户与admin角色的绑定关系
     * @date: 2022/3/4 10:35
     */
    private void saveAdminUserRoleRelation(Long userId, List<UserBindTenantVO> userBindTenantList) {
        if(CollectionUtils.isNotEmpty(userBindTenantList)) {
            userBindTenantList = userBindTenantList.stream().filter(userBindTenant -> userBindTenant.getIsAdmin().equals(IsAdminEnum.IS_ADMIN_YES.getCode())).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(userBindTenantList)) {
                List<BindUserRoleDTO> bindUserRoleList = roleMapper.selectAdminRoleIdByTenantId(userBindTenantList.stream().map(userBindTenant -> userBindTenant.getTenantId()).collect(Collectors.toList()));
                if(CollectionUtils.isEmpty(bindUserRoleList)) {
                    throw new ServiceException(UCResponseCode.TENANT_NOT_HAVE_ADMIN_ROLE);
                }
                List<UcUserRoleRelation> ucUserRoleRelationList = new ArrayList<>();
                for (BindUserRoleDTO bindUserRole : bindUserRoleList) {
                    UcUserRoleRelation ucUserRoleRelation = new UcUserRoleRelation();
                    ucUserRoleRelation.setUserId(userId);
                    ucUserRoleRelation.setRoleId(bindUserRole.getRoleId());
                    ucUserRoleRelation.setTenantId(bindUserRole.getTenantId());
                    ucUserRoleRelationList.add(ucUserRoleRelation);
                }
                if(CollectionUtils.isNotEmpty(ucUserRoleRelationList)) {
                    ucUserRoleRelationService.saveBatch(ucUserRoleRelationList);
                }
            }
        }
    }

    /**
     * @param userId
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 更新账户与租户的绑定关系
     * @date: 2022/3/8 15:00
     */
    private void updateUserTenantRelation(Long userId, List<UserBindTenantVO> userBindTenantList) {
        if(CollectionUtils.isNotEmpty(userBindTenantList)) {
            userTenaRelationMapper.deleteUserTenantRelationByUserId(userId, userBindTenantList.stream().map(userBindTenant -> userBindTenant.getTenantId()).collect(Collectors.toList()));
            saveUserTenantRelation(userId, userBindTenantList);
        }
    }

    /**
     * @param userId
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 更新admin账户与admin角色的绑定关系
     * @date: 2022/3/8 15:00
     */
    private void updateAdminUserRoleRelation(Long userId, List<UserBindTenantVO> userBindTenantList) {
        if(CollectionUtils.isNotEmpty(userBindTenantList)) {
            userRoleRelationMapper.deleteUserRoleRelationByUserId(userId, userBindTenantList.stream().map(userBindTenant -> userBindTenant.getTenantId()).collect(Collectors.toList()));
            saveAdminUserRoleRelation(userId, userBindTenantList);
        }
    }

    /**
     * @param userBindTenantList
     * @author: mike.ma
     * @return: void
     * @desc: 校验租户绑定列表中是否存在重复租户
     * @date: 2022/3/8 15:48
     */
    private void validateTenantRepeat(List<UserBindTenantVO> userBindTenantList) {
        HashSet tenantSet = new HashSet<>(userBindTenantList);
        Boolean result = tenantSet.size() == userBindTenantList.size() ? true : false;
        if(!result) {
            throw new ServiceException(UCResponseCode.TENANT_IS_REPEAT);
        }
    }

    public <T> T getResponseResult(WebResponse<T> response) {
        log.info("##### getResponseResult response.code：{}", response == null ? "repsonse is null" : response.getCode());
        return (response != null && response.getIsSuccess() && response.getResult() != null) ? response.getResult() : null;
    }

}

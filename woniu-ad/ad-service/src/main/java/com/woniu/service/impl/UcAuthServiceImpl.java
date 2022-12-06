package com.woniu.service.impl;

import com.alibaba.fastjson.JSON;
import com.woniu.JwtAuthConfig;
import com.woniu.dao.custom.TenantMapper;
import com.woniu.dto.LoginResultDTO;
import com.woniu.dto.TenantInfoDTO;
import com.woniu.entity.UcUser;
import com.woniu.enums.EntryEnum;
import com.woniu.enums.LoginTypeEnum;
import com.woniu.enums.UserTypeEnum;
import com.woniu.exception.ServiceException;
import com.woniu.extend.TokenUser;
import com.woniu.response.UCResponseCode;
import com.woniu.service.UcAuthService;
import com.woniu.service.UcUserService;
import com.woniu.utils.JWTUtils;
import com.woniu.utils.MD5Utils;
import com.woniu.vo.ValidateSmsAuthCodeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 授权登录Service实现  
 * @date: 2022-03-09 15:23  
 **************************************/
@Slf4j
@Service
public class UcAuthServiceImpl implements UcAuthService {

    @Autowired
    private JwtAuthConfig jwtAuthConfig;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private UcUserService ucUserService;

    @Override
    public LoginResultDTO login(String userName, String password, String mobile, String authCode, Integer loginType, Integer entry) {
        UcUser ucUser;
        if(loginType == LoginTypeEnum.USERNAME_PASSWORD_TYPE.ordinal()) {
            password = MD5Utils.stringToMD5(password);
            ucUser = ucUserService.getUcUserByUserNameAndPassword(userName, password);
            if(ucUser == null) {
                throw new ServiceException(UCResponseCode.USERNAME_OR_PASSWORD_IS_ERROR_OR_DISABLE);
            }
        } else {
            ucUser = ucUserService.getUcUserByMobile(mobile);
            if(ucUser == null) {
                throw new ServiceException(UCResponseCode.DONT_HAVE_USER_WITH_MOBILE);
            }
            ValidateSmsAuthCodeVO validateSmsAuthCode = new ValidateSmsAuthCodeVO(mobile, authCode);
            log.info("#### 校验短信验证码参数：{}", JSON.toJSONString(validateSmsAuthCode));
            /*MessageCenterResponse<Boolean> response = ucMessageCenterClient.validateSmsAuthCode(validateSmsAuthCode);
            log.info("#### 校验短信验证码返回值：{}", JSON.toJSONString(response));
            if(response == null || response.getIsSuccess() == null || !response.getIsSuccess()) {
                throw new ServiceException(new IResponseCode() {
                    public int getCode() {
                        return response.getCode();
                    }
                    public String getMessage() {
                        return response.getMessage();
                    }
                });
            }*/
        }
        //校验账户类型是否与管理端、租户端相同，如果不同，不允许登录
        if((ucUser.getType() == EntryEnum.B_PLATFORM_END.getCode() && entry == EntryEnum.B_TENANT_END.getCode())
                || (ucUser.getType() != EntryEnum.B_PLATFORM_END.getCode() && entry == EntryEnum.B_PLATFORM_END.getCode())) {
            throw new ServiceException(UCResponseCode.LOGIN_PLATFORM_IS_ERROR);
        }
        //根据账户ID查询所有可用的租户，如果没有可用的租户则不允许登录
        List<TenantInfoDTO> tenantInfoList = tenantMapper.selectAbleTenantInfoListByUserId(ucUser.getId());
        if(CollectionUtils.isEmpty(tenantInfoList)) {
            throw new ServiceException(UCResponseCode.ALL_TENANT_IS_DISABLED);
        }
        if(EntryEnum.B_TENANT_END.getCode() == entry && UserTypeEnum.USER_TYPE_TENANT.getCode() == ucUser.getType()) {
            tenantInfoList = tenantMapper.selectTenantInfoListByUserId(ucUser.getId());
        }
        TokenUser user = new TokenUser();
        user.setId(ucUser.getId());
        user.setUsername(ucUser.getUserName());
        String token = new JWTUtils().generateToken(user, jwtAuthConfig);

        return new LoginResultDTO(ucUser.getId(), ucUser.getUserName(), ucUser.getNickName(), token, tenantInfoList);
    }

}

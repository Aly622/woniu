package com.woniu.service;

import com.woniu.dto.LoginResultDTO;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 授权登录Service接口  
 * @date: 2022-03-09 15:21  
 **************************************/
public interface UcAuthService {

    /**
     * @param userName
     * @param password
     * @param mobile
     * @param authCode
     * @param loginType
     * @param entry
     * @author: mike.ma
     * @return: com.esmartwave.niumeng.diap.dto.LoginResultDTO
     * @desc: 授权登录
     * @date: 2022/4/25 12:06
     */
    LoginResultDTO login(String userName, String password, String mobile, String authCode, Integer loginType, Integer entry);

}

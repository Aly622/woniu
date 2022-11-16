package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 登录返回结果  
 * @date: 2022-03-09 15:36  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultDTO {

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 登录返回token
     */
    @ApiModelProperty(value = "登录返回token")
    private String token;

    /**
     * 该账户绑定的租户列表
     */
    @ApiModelProperty(value = "该账户绑定的租户列表")
    private List<TenantInfoDTO> tenantInfoList;
}

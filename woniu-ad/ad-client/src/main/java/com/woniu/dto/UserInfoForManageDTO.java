package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 账户详情对象  
 * @date: 2022-03-03 15:58  
 **************************************/
@Data
public class UserInfoForManageDTO {

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
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    /**
     * 账号类型（1：平台，2：租户）
     */
    @ApiModelProperty(value = "账号类型（1：平台，2：租户）")
    private Integer type;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 租户绑定列表
     */
    @ApiModelProperty(value = "租户绑定列表")
    private List<UserBindTenantDTO> userBindTenantList;
}

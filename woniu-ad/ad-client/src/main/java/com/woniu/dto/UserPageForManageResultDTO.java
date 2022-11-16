package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 用户分页查询结果对象（管理端） 
 * @date: 2022-03-03 10:32  
 **************************************/
@Data
public class UserPageForManageResultDTO {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
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
     * 账号类型（1：平台，2：租户，3：生产商）
     */
    @ApiModelProperty(value = "账号类型（1：平台，2：租户，3：生产商）")
    private Integer type;

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
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）")
    private Integer status;

    /**
     * 是否可编辑（0：可编辑，1：不可编辑）
     */
    @ApiModelProperty(value = "是否可编辑（0：可编辑，1：不可编辑）")
    private Integer allowEdit;
}

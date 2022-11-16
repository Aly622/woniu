package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新账户（租户端） 
 * @date: 2022-03-08 11:37  
 **************************************/
@Data
public class UpdateUserForTenantVO {

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID", required = true)
    @NotNull(message="账户ID不能为空")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank(message="用户名不能为空")
    private String userName;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", required = true)
    @NotBlank(message="昵称不能为空")
    private String nickName;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message="邮箱不能为空")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message="手机号不能为空")
    private String mobile;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImgUrl;

    /**
     * 账号类型（2：租户，3：生产商）
     */
    @ApiModelProperty(value = "账号类型（2：租户，3：生产商）", required = true)
    @NotNull(message="账号类型不能为空")
    private Integer type;

    /**
     * 组织ID或生产商ID
     */
    @ApiModelProperty(value = "组织ID或生产商ID", required = true)
    @NotNull(message="组织ID或生产商ID不能为空")
    private Long orgId;

    /**
     * 状态（0：停用，1：启用）
     */
    @ApiModelProperty(value = "状态（0：停用，1：启用）", required = true)
    @NotNull(message="状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}

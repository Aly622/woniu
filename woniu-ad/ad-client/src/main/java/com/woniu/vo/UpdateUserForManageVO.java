package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 更新账户入参对象（管理端） 
 * @date: 2022-03-04 10:45  
 **************************************/
@Data
public class UpdateUserForManageVO {

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
     * 账号类型（1：平台，2：租户）
     */
    @ApiModelProperty(value = "账号类型（1：平台，2：租户）", required = true)
    @NotNull(message="账号类型不能为空")
    private Integer type;

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

    /**
     * 租户绑定列表
     */
    @ApiModelProperty(value = "租户绑定列表")
    private List<UserBindTenantVO> userBindTenantList;
}

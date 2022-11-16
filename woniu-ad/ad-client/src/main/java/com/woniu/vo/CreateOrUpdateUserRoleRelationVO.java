package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 创建或更新用户与角色绑定关系对象  
 * @date: 2022-03-04 14:00  
 **************************************/
@Data
public class CreateOrUpdateUserRoleRelationVO {

    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID", required = true)
    @NotNull(message="账户ID不能为空")
    private Long userId;

    /**
     * 角色列表ID
     */
    @ApiModelProperty(value = "角色列表ID", required = true)
    @NotEmpty(message="角色列表ID不能为空")
    private List<Long> roleIdList;
}

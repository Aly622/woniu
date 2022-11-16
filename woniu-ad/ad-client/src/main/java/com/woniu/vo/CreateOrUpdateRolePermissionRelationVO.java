package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色与权限绑定对象  
 * @date: 2022-03-02 10:56  
 **************************************/
@Data
public class CreateOrUpdateRolePermissionRelationVO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message="角色ID不能为空")
    private Long roleId;

    /**
     * 权限ID列表
     */
    @ApiModelProperty(value = "权限ID列表")
    private List<Long> permissionIdList;
}

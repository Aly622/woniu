package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 权限树结构返回对象  
 * @date: 2022-03-01 18:15  
 **************************************/
@Data
public class PermissionTreeDTO {

    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private Long id;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * 菜单类型（1：菜单，2：按钮）
     */
    @ApiModelProperty(value = "菜单类型（1：菜单，2：按钮）")
    private Integer type;

    /**
     * 父菜单ID
     */
    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    /**
     * 子权限树形结构列表
     */
    @ApiModelProperty(value = "子权限树形结构列表")
    private List<PermissionTreeDTO> child;

    public PermissionTreeDTO(Long id, String name, Integer type, Long parentId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parentId = parentId;
    }
}

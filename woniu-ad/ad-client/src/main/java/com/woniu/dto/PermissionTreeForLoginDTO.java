package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 登录返回的权限树  
 * @date: 2022-03-09 17:20  
 **************************************/
@Data
public class PermissionTreeForLoginDTO {

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
     * 权限code
     */
    @ApiModelProperty(value = "权限code")
    private Integer code;

    /**
     * 菜单类型（1：菜单，2：按钮）
     */
    @ApiModelProperty(value = "菜单类型（1：菜单，2：按钮）")
    private Integer type;

    /**
     * 父权限ID
     */
    @ApiModelProperty(value = "父权限ID")
    private Long parentId;

    /**
     * 菜单跳转URL
     */
    @ApiModelProperty(value = "菜单跳转URL")
    private String url;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value = "显示顺序")
    private Integer displayOrder;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 子权限列表
     */
    @ApiModelProperty(value = "子权限列表")
    private List<PermissionTreeForLoginDTO> childPermissionList;

    public PermissionTreeForLoginDTO(Long id, String name, Integer code, Integer type, Long parentId, String url, Integer displayOrder, String icon) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.parentId = parentId;
        this.url = url;
        this.displayOrder = displayOrder;
        this.icon = icon;
    }
}

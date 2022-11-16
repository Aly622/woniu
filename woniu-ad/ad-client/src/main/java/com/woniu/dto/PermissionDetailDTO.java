package com.woniu.dto;

import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 权限结果对象  
 * @date: 2022-03-01 18:02  
 **************************************/
@Data
public class PermissionDetailDTO {

    /**
     * 权限ID
     */
    private Long id;

    /**
     * 权限ID
     */
    private String name;

    /**
     * 菜单类型（1：菜单，2：按钮）
     */
    private Integer type;

    /**
     * 父菜单ID
     */
    private Long parentId;
}

package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色结果对象  
 * @date: 2022-03-02 16:10  
 **************************************/
@Data
public class RoleInfoDTO {

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
}

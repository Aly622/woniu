package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色查询对象  
 * @date: 2022-03-04 14:19  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryVO {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
}

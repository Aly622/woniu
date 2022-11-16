package com.woniu.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 权限ID列表返回对象  
 * @date: 2022-03-24 11:21  
 **************************************/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionIdListDTO {

    /**
     * 权限ID列表
     */
    @ApiModelProperty(value = "权限ID列表")
    private List<Long> permissionIdList;
}

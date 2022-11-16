package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 角色分页查询入参对象  
 * @date: 2022-02-28 17:29  
 **************************************/
@Data
public class RolePageQueryVO extends Page {

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
}

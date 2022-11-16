package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 审批流配置分页查询  
 * @date: 2022-03-02 15:26  
 **************************************/
@Data
public class AuditConfigPageQueryVO extends Page {

    /**
     * 审批流名称
     */
    @ApiModelProperty(value = "审批流名称")
    private String name;
}

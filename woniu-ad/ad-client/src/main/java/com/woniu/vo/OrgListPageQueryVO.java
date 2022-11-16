package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc:  组织架构分页查询对象
 * @date: 2022-02-25 11:10  
 **************************************/
@Data
public class OrgListPageQueryVO extends Page {

    /**
     * 组织名称
     */
    @ApiModelProperty(value = "组织名称")
    private String orgName;

    /**
     * 父组织ID
     */
    @ApiModelProperty(value = "父组织ID")
    private Long parentId;

    /**
     * orgId列表
     */
    @ApiModelProperty(value = "orgId列表，与前端无关")
    private List<Long> orgIdList;

}

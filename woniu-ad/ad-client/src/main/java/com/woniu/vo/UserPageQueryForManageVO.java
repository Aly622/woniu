package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/************************************** 
 * @program: diap
 * @author: mike.ma
 * @desc: 账号分页查询入参对象  
 * @date: 2022-03-03 10:41  
 **************************************/
@Data
public class UserPageQueryForManageVO extends Page {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 账号类型（1：平台，2：租户，3：生产商）
     */
    @ApiModelProperty(value = "账号类型（1：平台，2：租户，3：生产商）")
    private Integer type;
}

package com.woniu.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class AuthorizaWechatVO {

    @ApiModelProperty(value = "租户id", required = true)
    private Long tenantId;

    @ApiModelProperty(value = "微信信息集合")
    private List<SaveTenantWchatRelationVO> wechatInfo;
}

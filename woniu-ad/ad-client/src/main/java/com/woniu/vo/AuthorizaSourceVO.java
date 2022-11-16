package com.woniu.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AuthorizaSourceVO {

    @ApiModelProperty(value = "租户id", required = true)
    private Long tenantId;

    @ApiModelProperty(value = "资源id集合")
    private List<Long> sourceIds;
}

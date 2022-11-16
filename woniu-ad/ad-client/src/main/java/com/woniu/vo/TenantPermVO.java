package com.woniu.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TenantPermVO {

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id",required = true)
    @NotNull(message="租户id不能为空.")
    private Long tenantId;

    /**
     * 权限id集合
     */
    @ApiModelProperty(value = "权限id集合",required = true)
    @NotNull(message="权限id集合不能为空.")
    private List<Long> permIds;


}

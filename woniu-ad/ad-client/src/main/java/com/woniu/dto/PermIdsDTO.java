package com.woniu.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PermIdsDTO {
    @ApiModelProperty(value = "权限id集合")
    private List<Long> permIds;
}

package com.woniu.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PermIdsDTO {
    @ApiModelProperty(value = "æéidéå")
    private List<Long> permIds;
}

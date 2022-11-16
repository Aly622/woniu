package com.woniu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * 分页获取租户列表VO
 */
@Data
public class TenantPageVO extends Page {

    /**
     * 租户名称
     */
    private String name;
}

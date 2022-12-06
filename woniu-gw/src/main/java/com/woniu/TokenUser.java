package com.woniu;

import lombok.Data;

/**
 * @ClassName TokenUser
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/8/23 15:31
 * @Version 1.0
 **/
@Data
public class TokenUser {
    private Integer entry;
    private String id;
    private String username;
    private String password;
    private Long tenantId;//所选部门
    private Long campaignId;//所选活动
    private String apiToken;
}

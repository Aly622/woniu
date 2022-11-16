package com.woniu.contants;

/**
 * @ClassName CommonConstants
 * @Desc TODO
 * @Author Oliver.Liu
 * @Date 2019/6/14 17:21
 * @Version 1.0
 **/
public interface CommonConstants {
    //请求头说明
    /**
     * 来源  1-B端   2-C端
     */
    String ENTRY = "Entry";
    /**
     * 租户编号
     */
    String TENANTID = "TenantId";
    /**
     * token
     */
    String OAUTH_TOKEN = "Authorization";
    /**
     * apiToken
     */
    String API_TOKEN = "ApiToken";
    /**
     * source
     */
    String CAMPAIGNID = "CampaignId";
    /*
     * 当前用户 和 CommonConstants中的USER_ID值一致
     **/
    String USER_ID = "CurrentUser";
    /*
     **/
    String REFERER = "Referer";
    /*
     **/
    final String ORIGIN = "Origin";
    //数据正常状态
    Integer IS_DELETED = 0;
    //是否需要邮件提醒
    String MAIL_NOTIFY= "1";
    // 生码顺序、层级分隔符
    String CODE_SPLIT = ":";
}

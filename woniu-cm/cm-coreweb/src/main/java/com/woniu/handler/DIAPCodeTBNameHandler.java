package com.woniu.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName DIAPCodeTBNameHandler.java
 * @Description TODO
 * @createTime 2022年03月29日 17:20:00
 */
public class DIAPCodeTBNameHandler implements TableNameHandler {

    //使用ThreadLocal防止多线程相互影响
    private static ThreadLocal<Long> campaignId = new ThreadLocal<Long>();

    public static void setCampaignId(Long campaignIdVal) {
        campaignId.set(campaignIdVal);
    }

    @Override
    public String dynamicTableName(String sql, String tableName) {
        Long campaignIdVal = campaignId.get();
        if (campaignIdVal == null) {
            throw new RuntimeException("码明细动态表名未设置活动ID");
        } else {
            String suffix = String.valueOf(campaignIdVal);
            //这里清除ThreadLocal的值，防止线程复用出现问题
            campaignId.set(null);
            return tableName + "_" + suffix;
        }
    }
}

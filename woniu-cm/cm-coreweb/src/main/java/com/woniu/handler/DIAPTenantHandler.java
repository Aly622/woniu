package com.woniu.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.woniu.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.NullValue;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Oliver.liu
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2021/1/617:06
 */
@Slf4j
@Component
public class DIAPTenantHandler implements TenantLineHandler {
    private static final String TENANT_COLUMN = "tenant_id";
    //排除不需要区分租户的表
    private static final List<String> IGNORE_TENANT_TABLES =
            Arrays.asList("bc_cust_table",
                            "uc_tenant",
                            "bc_province_city_district",
                            "uc_perm_group_relation",
                            "uc_permission_group",
                            "uc_user");
    /**
     * 获取租户 ID 值表达式，支持多个 ID 条件查询
     * 支持自定义表达式，比如：tenant_id in (1,2)
     * @param //where 参数 true 表示为 where 条件 false 表示为 insert 或者 select 条件
     * @return 租户 ID 值表达式
     */
    @Override
    public Expression getTenantId() {

        //可以通过过滤器从请求中获取对应租户id
        Long tenantId = SecurityUtils.getUser().getTenantId();

        log.debug("当前租户编号为{}", tenantId);

        if (tenantId == null) {
            return new NullValue();
        }
        return new LongValue(tenantId);
    }

    //获取租户字段名
    @Override
    public String getTenantIdColumn() {
        return TENANT_COLUMN;
    }
    // 根据表名判断是否进行过滤
    @Override
    public boolean ignoreTable(String tableName) {
        // 过滤掉一些表：如租户表（provider）本身不需要执行这样的处理。
        return IGNORE_TENANT_TABLES.stream().anyMatch((e) -> e.equalsIgnoreCase(tableName));
    }
}

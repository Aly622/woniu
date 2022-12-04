package com.woniu.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.LogicDeleteByIdWithFill;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.woniu.handler.DIAPCodeTBNameHandler;
import com.woniu.handler.DIAPTenantHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName MyBatisPlusConfig.java
 * @Description TODO
 * @createTime 2022年02月18日 10:46:00
 */
@MapperScan("com.woniu.dao")
public class MyBatisPlusConfig {

    @Autowired
    private DIAPTenantHandler diapTenantHandler;

    /**
     * 是否开启租户模式
     */
    private Boolean enable = true;
    //逻辑删除 同时更新时间
    @Bean
    public DefaultSqlInjector defaultSqlInjector() {
        DefaultSqlInjector defaultSqlInjector = new  DefaultSqlInjector () {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
                List<AbstractMethod> methods= super.getMethodList(mapperClass);
                methods.add(new LogicDeleteByIdWithFill());//删除时也更新最近更新时间
                methods.add(new InsertBatchSomeColumn()); // 添加InsertBatchSomeColumn方法
                return methods;
            }
        };
        return defaultSqlInjector;
    }
    // 分页插件
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        if (enable) {
            mybatisPlusInterceptor.addInnerInterceptor(new TenantLineInnerInterceptor(diapTenantHandler));
        }
        //1-分页拦截器
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //设置请求页面大于最大页后操作，true调回到首页，false 继续请求  默认false
        paginationInnerInterceptor.setOverflow(false);
        //设置最大单页限制数量， 默认500条， -1 不受限制
        paginationInnerInterceptor.setMaxLimit(500L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        //2-动态表名拦截器
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        HashMap<String, TableNameHandler> nameHandlerHashMap = new HashMap<String, TableNameHandler>();
        //这里为不同的表设置对应表名处理器
        nameHandlerHashMap.put("gc_code", new DIAPCodeTBNameHandler());
        nameHandlerHashMap.put("gc_code_external", new DIAPCodeTBNameHandler());
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(nameHandlerHashMap);
        mybatisPlusInterceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        return mybatisPlusInterceptor;
    }
}

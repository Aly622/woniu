package com.woniu.config;

import org.mybatis.spring.annotation.MapperScan;


/**
 * @ClassName MyBatisConfig
 * @Desc 数据库配置
 * @Author Oliver.Liu
 * @Date 2019/6/9 17:41
 * @Version 1.0
 **/
//@EnableTransactionManagement
@MapperScan({"com.esmartwave.niumeng.diap.dao"})//这个注解，作用相当于下面的@Bean MapperScannerConfigurer，2者配置1份即可
public class MyBatisPlusConfig {

}

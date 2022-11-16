package com.woniu.mybatisplus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @ClassName MyBatisConfig
 * @Desc 代码生成器属性配置
 * @Author Oliver.Liu
 * @Date 2019/6/8 21:58
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "mybatisgenerator.cfg")
@Data
public class MyBatisPropertyConfig {
    private String author;  //作者
    private String tables;
    //文件名配置
    private String mapperName;
    private String serviceName;
    private String serviceImplName;
    private String controllerName;
    //数据库相关
    private String driverName;
    private String username;
    private String password;
    private String url;
    //包配置
    private String moduleName;
    private String parentPackage;
    private String controller;
    private String entity;
    private String mapper;
    private String service;
    private String serviceImpl;
    //基类
    private String superEntityClass;
    private String superMapperClass;
    private String superEntityColumns;
    private String superServiceImplClass;
    private String superControllerClass;
    //自定义输出文件目录
    private String outputDir;
    private String outputFile;
    private String outputEntityDir;
    //是否只生成entity和mapper
    private Boolean cleanEntity;
    private Boolean cleanMapper;
}

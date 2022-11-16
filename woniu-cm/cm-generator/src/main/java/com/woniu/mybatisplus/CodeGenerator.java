package com.woniu.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.Collections;

/**
 * @ClassName CodeGenerator
 * @Desc orm生成器
 * @Author Oliver.Liu
 * @Date 2019/6/8 16:20
 * @Version 1.0
 **/
public class CodeGenerator {
    public static void generateEntity(MyBatisPropertyConfig myBatisPropertyConfig) {
       // String projectPath ="F:\\codes";
        String projectPath = System.getProperty("user.dir");
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setMapper(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setEntity("/templates/entity.java.vm");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(
        ).setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(projectPath + myBatisPropertyConfig.getOutputEntityDir())
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setSwagger2(true)
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setAuthor(myBatisPropertyConfig.getAuthor())
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setIdType(IdType.ASSIGN_ID)
                        .setMapperName(myBatisPropertyConfig.getMapperName())
                        .setServiceName(myBatisPropertyConfig.getServiceName())
                        .setServiceImplName(myBatisPropertyConfig.getServiceImplName())
                        .setControllerName(myBatisPropertyConfig.getControllerName())
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)// 数据库类型
                        .setDriverName(myBatisPropertyConfig.getDriverName())
                        .setUsername(myBatisPropertyConfig.getUsername())
                        .setPassword(myBatisPropertyConfig.getPassword())
                        .setUrl(myBatisPropertyConfig.getUrl())
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        //.setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setInclude(myBatisPropertyConfig.getTables().split(",")) // 需要生成的表
                        .setRestControllerStyle(true)
                        .setLogicDeleteFieldName("is_deleted")
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        .setSuperEntityColumns(myBatisPropertyConfig.getSuperEntityColumns().split(","))
                        .setSuperEntityClass(myBatisPropertyConfig.getSuperEntityClass())
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        .setModuleName(myBatisPropertyConfig.getModuleName())
                        .setParent(myBatisPropertyConfig.getParentPackage())// 自定义包路径
                        .setEntity(myBatisPropertyConfig.getEntity())

        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                    }
                }.setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        checkDirs(filePath);
                        // 判断自定义文件夹是否需要创建,这里调用默认的方法
//                      //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                        File file = new File(filePath);
                        boolean exist = file.exists();
                        if (exist) {
                            if (myBatisPropertyConfig.getCleanEntity() && FileType.ENTITY == fileType) {
                                return true;
                            }
                            return false;
                        }
                        //不存在的文件都需要创建
                        return true;
                    }
                })
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                templateConfig
        );
        // 执行生成
        mpg.execute();
        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println("entity done.");
    }

    public static void generateVCObject(MyBatisPropertyConfig myBatisPropertyConfig) {
        // String projectPath ="F:\\codes";
        String projectPath = System.getProperty("user.dir");
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setEntity(null);
        templateConfig.setMapper("/templates/controller.java.vm");
        templateConfig.setMapper("/templates/mapper.java.vm");
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(
        ).setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setOutputDir(projectPath + myBatisPropertyConfig.getOutputDir())
                        .setFileOverride(true)// 是否覆盖文件
                        .setActiveRecord(true)// 开启 activeRecord 模式
                        .setSwagger2(true)
                        .setEnableCache(false)// XML 二级缓存
                        .setBaseResultMap(true)// XML ResultMap
                        .setBaseColumnList(true)// XML columList
                        .setOpen(false)//生成后打开文件夹
                        .setAuthor(myBatisPropertyConfig.getAuthor())
                        // 自定义文件命名，注意 %s 会自动填充表实体属性！
                        .setMapperName(myBatisPropertyConfig.getMapperName())
                        .setServiceName(myBatisPropertyConfig.getServiceName())
                        .setServiceImplName(myBatisPropertyConfig.getServiceImplName())
                        .setControllerName(myBatisPropertyConfig.getControllerName())
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL)// 数据库类型
                        .setDriverName(myBatisPropertyConfig.getDriverName())
                        .setUsername(myBatisPropertyConfig.getUsername())
                        .setPassword(myBatisPropertyConfig.getPassword())
                        .setUrl(myBatisPropertyConfig.getUrl())
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        //.setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setColumnNaming(NamingStrategy.underline_to_camel)
                        .setInclude(myBatisPropertyConfig.getTables().split(",")) // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        .setSuperMapperClass(myBatisPropertyConfig.getSuperMapperClass())
                        .setSuperServiceImplClass(myBatisPropertyConfig.getSuperServiceImplClass())
                        .setSuperControllerClass(myBatisPropertyConfig.getSuperControllerClass())
        ).setPackageInfo(
                // 包配置
                new PackageConfig()
                        .setModuleName(myBatisPropertyConfig.getModuleName())
                        .setParent(myBatisPropertyConfig.getParentPackage())// 自定义包路径
                        .setController(myBatisPropertyConfig.getController())// 这里是控制器包名，默认 web
                        .setMapper(myBatisPropertyConfig.getMapper())
                        .setService(myBatisPropertyConfig.getService())
                        .setServiceImpl(myBatisPropertyConfig.getServiceImpl())
        ).setCfg(
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig("/templates/mapper.xml.vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return projectPath + myBatisPropertyConfig.getOutputFile() + tableInfo.getEntityName() + "Mapper.xml";
                    }
                })).setFileCreate(new IFileCreate() {
                    @Override
                    public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                        checkDirs(filePath);
                        // 判断自定义文件夹是否需要创建,这里调用默认的方法
//                      //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                        File file = new File(filePath);
                        boolean exist = file.exists();
                        if (exist) {
                            if(myBatisPropertyConfig.getCleanMapper()
                                    && (FileType.MAPPER == fileType || filePath.endsWith("Mapper.xml"))) {
                                return true;
                            }
                            return false;
                        }
                        //不存在的文件都需要创建
                        return true;
                    }
                })
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                templateConfig
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );
        // 执行生成
        mpg.execute();
        // 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
        System.err.println("mvc done.");
    }
    static void checkDirs(String filePath) {
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            file.getParentFile().mkdirs();
        }
    }
}

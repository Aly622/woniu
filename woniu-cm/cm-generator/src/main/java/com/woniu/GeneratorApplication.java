package com.woniu;

import com.esmartwave.niumeng.diap.utils.SpringAppContextUtils;
import com.woniu.mybatisplus.CodeGenerator;
import com.woniu.mybatisplus.MyBatisPropertyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeneratorApplication {
    @Autowired
    MyBatisPropertyConfig myBatisPropertyConfig;

    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
        CodeGenerator.generateEntity(SpringAppContextUtils.getBean(MyBatisPropertyConfig.class));
        CodeGenerator.generateVCObject(SpringAppContextUtils.getBean(MyBatisPropertyConfig.class));
        System.out.println("done.");
    }
}

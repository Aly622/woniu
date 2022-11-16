package com.woniu.config;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 读取配置数据 用于动态参数
@Data
@ConfigurationProperties(prefix = "mail")
public class DynamicsValConfig {
    public static String subjectSource;
    public static String enableMailNotify;
    public static Boolean replaceAll;
}
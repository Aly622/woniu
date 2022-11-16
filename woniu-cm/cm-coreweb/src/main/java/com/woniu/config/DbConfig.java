package com.woniu.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yu.liu
 * @version 1.0.0
 * @ClassName NacosConfig.java
 * @Description db配置文件
 * @createTime 2022年02月22日 13:41:00
 */
@Configuration
public class DbConfig {
    @Value("${mysql.diap.master.username:default}")
    private String masterUsername;

    @Value("${mysql.diap.master.hostname:default}")
    private String masterHostname;

    @Value("${mysql.diap.master.password:default}")
    private String masterPassword;

    @Value("${mysql.diap.master.port:default}")
    private String masterPort;

    @Value("${mysql.diap.master.driverClassName:default}")
    private String masterDriverClassName;

    @Value("${mysql.diap.slave.username:default}")
    private String slaveUsername;

    @Value("${mysql.diap.slave.hostname:default}")
    private String slaveHostname;

    @Value("${mysql.diap.slave.password:default}")
    private String slavePassword;

    @Value("${mysql.diap.slave.port:default}")
    private String slavePort;

    @Value("${mysql.diap.slave.driverClassName:default}")
    private String slaveDriverClassName;

    public String getMasterUsername() {
        return masterUsername;
    }

    public String getMasterHostname() {
        return masterHostname;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public String getMasterPort() {
        return masterPort;
    }

    public String getMasterDriverClassName() {
        return masterDriverClassName;
    }

    public String getSlaveUsername() {
        return slaveUsername;
    }

    public String getSlaveHostname() {
        return slaveHostname;
    }

    public String getSlavePassword() {
        return slavePassword;
    }

    public String getSlavePort() {
        return slavePort;
    }

    public String getSlaveDriverClassName() {
        return slaveDriverClassName;
    }
}

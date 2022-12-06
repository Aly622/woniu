package com.woniu;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Oliver.liu
 * @version 1.0.0
 * @ClassName JwtAuthConfig.java
 * @Description TODO
 * @createTime 2022年02月24日 13:42:00
 */
@Data
@ConfigurationProperties(prefix = "security.jwt")
public class JwtAuthConfig {
    private String url;

    private String refreshurl;

    private String header;

    private String prefix;

    private int expiration; // default 24 hours

    private String secret;
}

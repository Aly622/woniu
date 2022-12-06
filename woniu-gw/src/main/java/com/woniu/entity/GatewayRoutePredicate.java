package com.woniu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
@Data
public class GatewayRoutePredicate implements Serializable {

    private Long id;

    private Long gatewayId;

    private String predicateKey;

    private String predicateValue;

    private Date createdAt;
}

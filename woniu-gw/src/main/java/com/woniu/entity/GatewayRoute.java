package com.woniu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
@Data
public class GatewayRoute implements Serializable {

    private Long id;

    private String gatewayRouteId;

    private String gatewayRouteUri;

    private Integer gatewayRouteOrder;

    private Date createdAt;
}

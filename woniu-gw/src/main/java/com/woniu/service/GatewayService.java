package com.woniu.service;


import com.woniu.entity.GatewayRoute;
import com.woniu.entity.GatewayRoutePredicate;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
public interface GatewayService {
    Map<GatewayRoute, List<GatewayRoutePredicate>> listForRoutes();
}

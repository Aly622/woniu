package com.woniu.service.impl;

import com.woniu.entity.GatewayRoute;
import com.woniu.entity.GatewayRoutePredicate;
import com.woniu.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
@Slf4j
@Service
public class GatewayServiceImpl implements GatewayService {

    final String GATEWAY_ROUTE_SQL = "SELECT * FROM gw_route WHERE is_open = 0";

    final String GATEWAY_ROUTE_PREDICATE_SQL = "SELECT * FROM gw_route_predicate WHERE is_open = 0";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<GatewayRoute, List<GatewayRoutePredicate>> listForRoutes() {
        Map<GatewayRoute, List<GatewayRoutePredicate>> map = new LinkedHashMap<>();
        List<GatewayRoute> routes = jdbcTemplate.query(GATEWAY_ROUTE_SQL, new BeanPropertyRowMapper<>(GatewayRoute.class));
        List<GatewayRoutePredicate> predicates = jdbcTemplate.query(GATEWAY_ROUTE_PREDICATE_SQL, new BeanPropertyRowMapper<>(GatewayRoutePredicate.class));
        for (GatewayRoute route : routes) {
            map.put(route, predicates.stream()
                    .filter(item -> route.getId().equals(item.getGatewayId()))
                    .collect(Collectors.toList()));
        }
        return map;
    }
}

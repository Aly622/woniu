package com.woniu.config.route;

import com.woniu.entity.GatewayRoute;
import com.woniu.entity.GatewayRoutePredicate;
import com.woniu.service.GatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
@Slf4j
@Configuration
@Primary
public class DefaultDynamicRouteDefinitionRepository implements DynamicRouteDefinitionRepository {

    @Autowired
    private GatewayService gatewayService;
    @Autowired
    private RouteDefinitionRepository routeDefinitionRepository;

    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void loadOrRefreshRouteDefinition() {
        log.debug("==========> Beginning Refresh Gateway Routes ...");
        Map<GatewayRoute, List<GatewayRoutePredicate>> listMap = gatewayService.listForRoutes();
        if (listMap != null && !listMap.isEmpty()) {
            Iterator<Map.Entry<GatewayRoute, List<GatewayRoutePredicate>>> iterator = listMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<GatewayRoute, List<GatewayRoutePredicate>> grNext = iterator.next();
                GatewayRoute gatewayRoute = grNext.getKey();
                List<GatewayRoutePredicate> gatewayRoutePredicates = grNext.getValue();
                if (CollectionUtils.isEmpty(gatewayRoutePredicates)) {
                    continue;
                }
                RouteDefinition routeDefinition = new RouteDefinition();
                routeDefinition.setId(gatewayRoute.getGatewayRouteId());
                routeDefinition.setOrder(gatewayRoute.getGatewayRouteOrder());
                try {
                    routeDefinition.setUri(new URI(gatewayRoute.getGatewayRouteUri()));
                } catch (URISyntaxException e) {
                    log.error(e.getMessage(), e);
                    continue;
                }

                // 断言名称归类
                Map<String, List<GatewayRoutePredicate>> routePredicateMap = new LinkedHashMap<>();
                for (GatewayRoutePredicate gatewayRoutePredicate : gatewayRoutePredicates) {
                    String predicateKey = gatewayRoutePredicate.getPredicateKey();
                    List<GatewayRoutePredicate> predicates = routePredicateMap.get(predicateKey);
                    if (predicates == null) {
                        predicates = new ArrayList<>();
                    }
                    predicates.add(gatewayRoutePredicate);
                    routePredicateMap.put(predicateKey, predicates);
                }
                List<PredicateDefinition> predicateDefinitions = new ArrayList<>();

                // 将归类好的断言类进行组装成PredicateDefinition断言类
                Iterator<Map.Entry<String, List<GatewayRoutePredicate>>> entryIterator = routePredicateMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, List<GatewayRoutePredicate>> next = entryIterator.next();
                    String key = next.getKey();
                    List<GatewayRoutePredicate> value = next.getValue();
                    PredicateDefinition predicateDefinition = new PredicateDefinition();
                    predicateDefinition.setName(key);
                    Map<String, String> args = new LinkedHashMap<>();
                    if (!CollectionUtils.isEmpty(value)) {
                        for (int i = 0; i < value.size(); i++) {
                            GatewayRoutePredicate gatewayRoutePredicate = value.get(i);
                            String predicateValue = gatewayRoutePredicate.getPredicateValue();
                            args.put(NameUtils.generateName(i), predicateValue);
                        }
                    }
                    predicateDefinition.setArgs(args);
                    predicateDefinitions.add(predicateDefinition);
                }
                routeDefinition.setPredicates(predicateDefinitions);

                List<FilterDefinition> filters = new ArrayList();
                //增加header跨域去重
                FilterDefinition filter = new FilterDefinition();
                Map<String, String> args = new LinkedHashMap<>();
                String predicateValue;
                //如果是swagger需要设置过滤器
                if(StringUtils.endsWithIgnoreCase(gatewayRoute.getGatewayRouteId(),"swagger")) {
                    filter = new FilterDefinition();
                    filter.setName("StripPrefix");
                    args = new LinkedHashMap<>();
                    predicateValue = "1";
                    args.put(NameUtils.generateName(0), predicateValue);
                    filter.setArgs(args);
                    filters.add(filter);
                    routeDefinition.setFilters(filters);
                }
                // 发布事件更新路由
                routeDefinitionRepository.save(Mono.just(routeDefinition)).subscribe();
                this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            }
            log.debug("Gateway selected by MySql is {}", listMap.size());
        } else {
            log.debug("Gateway selected by MySql is 0");
        }
        log.debug("Gateway Routes Refreshed successfully ! <==========");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}

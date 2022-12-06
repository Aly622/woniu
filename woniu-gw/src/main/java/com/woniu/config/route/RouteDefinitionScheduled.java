package com.woniu.config.route;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/11
 */
@Slf4j
@Component
public class RouteDefinitionScheduled {

    @Autowired
    private DynamicRouteDefinitionRepository dynamicRouteDefinitionRepository;

    @PostConstruct
    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshRouteDefinition() {
        dynamicRouteDefinitionRepository.loadOrRefreshRouteDefinition();
    }

}

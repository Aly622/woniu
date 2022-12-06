package com.woniu.config.route;

import org.springframework.context.ApplicationEventPublisherAware;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
public interface DynamicRouteDefinitionRepository extends ApplicationEventPublisherAware {

    /**
     * 加载或刷新路由断言
     */
    void loadOrRefreshRouteDefinition();

}

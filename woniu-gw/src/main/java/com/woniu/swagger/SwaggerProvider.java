package com.woniu.swagger;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sywd
 */
@Slf4j
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";
    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;
    private final DiscoveryClient discoveryClient;

    @Value("${spring.application.name:'diap-gw'}")
    private String applicationName;

    public SwaggerProvider(RouteLocator routeLocator, GatewayProperties gatewayProperties, DiscoveryClient discoveryClient) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<SwaggerResource> get() {
        if (discoveryClient == null) {
            return this.getResourceForProperties();
        } else {
            // 从注册中心上获取服务列表
            return this.getResourceForDiscoveryClient();
        }
    }

    private List<SwaggerResource> getResourceForDiscoveryClient() {
        List<String> services = discoveryClient.getServices()
                .stream()
                .filter(serviceName -> !StringUtils.equalsIgnoreCase(serviceName, applicationName))
                .collect(Collectors.toList());

        return CollectionUtils.isEmpty(services) ? this.getResourceForProperties() : services.stream()
                .map(service -> this.swaggerResource(service, "/" + service + API_URI))
                .collect(Collectors.toList());
    }

    private List<SwaggerResource> getResourceForProperties() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        //log.info("name:{},location:{}",name,location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}

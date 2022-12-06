package com.woniu.controller;

import com.woniu.config.route.DynamicRouteDefinitionRepository;
import com.woniu.response.WebResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:xiaojie.li@esmartwave.com">Colin</a>
 * <br/>Created in 2019/11/8
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/sysGateway")
public class GatewayController {

    @Autowired
    private DynamicRouteDefinitionRepository repository;

    @PostMapping("/reload")
    public WebResponse reload() {
        if (repository != null) {
            repository.loadOrRefreshRouteDefinition();
            return WebResponse.success("Gateway reloading,please hold on...");
        } else {
            log.error("DynamicGatewayCustomConfiguration is null");
            return WebResponse.fail("Gateway was reloaded fail");
        }
    }

}

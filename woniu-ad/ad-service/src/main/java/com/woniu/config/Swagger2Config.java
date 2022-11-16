package com.woniu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * @Author Oliver.Liu
 * @Desc //API接口描述
 * @Date 2019/6/14 17:18
 * @Param
 * @return
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config implements WebMvcConfigurer {
    public String title = "用户中心";
    public String description = "用户中心";
    public String basePackage = "com.esmartwave.niumeng.diap";
  /*  @Value("${spring.application.name}")
    String groupName;*/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                //.termsOfServiceUrl("http://192.168.199.228")
                .version("1.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置swagger静态资源访问路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}

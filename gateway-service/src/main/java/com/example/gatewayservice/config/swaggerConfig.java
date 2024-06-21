package com.example.gatewayservice.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Component
@Primary
@AllArgsConstructor
public class swaggerConfig implements SwaggerResourcesProvider {

    /**
     * swagger2默认的url后缀（v2或v3）
     */
    public static final String API_URI = "/v2/api-docs";

    /**
     * 获取路由信息和网关配置信息
     */
    private final RouteLocator routeLocator;

    private final GatewayProperties gatewayProperties;

    /**
     * 汇总所有微服务的swagger文档路径(访问swagger页面时，会调用此方法获取docs，该方法会返回一个包含所有swagger资源的列表)
     *
     * @return
     */
    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();

        // 存储路由信息
        List<String> routes = new LinkedList<>();

        // 取出gateway的routes，获取所有路由信息并将路由id加入到routes列表中
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        /**
         * 遍历网关配置中的路由信息，过滤掉不包含在 routes 列表中的路由。
         * 对于每个有效的路由，获取其谓词（Predicate）信息，筛选出 Path 谓词，
         * 并根据路由的 ID 构建 Swagger 资源对象，最后添加到 resources 列表中。
         */
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                        .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                                "/" + routeDefinition.getId() + API_URI))));
        System.out.println("*****************************************");
        for (int i = 0; i < resources.size(); i++) {
            System.out.println(resources.get(i).getName());
        }
        System.out.println("*****************************************");
        return resources;
    }

    // 用于创建并返回一个 Swagger 资源对象，其中包含了名称、位置和 Swagger 版本等信息
    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}

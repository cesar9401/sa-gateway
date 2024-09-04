package com.cesar31.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "sa-organization",
                        r -> r.path("/api/sa-organization/public/**")
                                .uri("lb://sa-organization")
                )
                .route(
                        "sa-organization",
                        r -> r.path("/api/sa-organization/organizations/**")
                                .uri("lb://sa-organization")
                )
                .route(
                        "sa-organization",
                        r -> r.path("/api/sa-organization/categories/**")
                                .uri("lb://sa-organization")
                )
                .route(
                        "sa-root",
                        r -> r.path("/api/sa-root/public/**")
                                .uri("lb://sa-root")
                )
                .route(
                        "sa-root",
                        r -> r.path("/api/sa-root/auth/**")
                                .uri("lb://sa-root")
                )
                .route(
                        "sa-root",
                        r -> r.path("/api/sa-root/clients/**")
                                .uri("lb://sa-root")
                )
                .route(
                        "sa-root",
                        r -> r.path("/api/sa-root/employees/**")
                                .uri("lb://sa-root")
                )
                .build();
    }
}

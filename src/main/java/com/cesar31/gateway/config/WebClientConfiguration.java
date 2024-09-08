package com.cesar31.gateway.config;

import com.cesar31.gateway.security.AuthenticationFilter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    private final AuthenticationFilter authenticationFilter;

    public WebClientConfiguration(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                /* sa-root */
                .route("sa-root", r -> r.path("/api/sa-root/auth/**").uri("lb://sa-root"))
                .route("sa-root", r -> r.path("/api/sa-root/public/**").uri("lb://sa-root"))
                .route("sa-root", r -> r.path("/api/sa-root/roles/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-root"))
                .route("sa-root", r -> r.path("/api/sa-root/clients/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-root"))
                .route("sa-root", r -> r.path("/api/sa-root/employees/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-root"))

                /* sa-organization */
                .route("sa-organization", r -> r.path("/api/sa-organization/public/**").uri("lb://sa-organization"))
                .route("sa-organization", r -> r.path("/api/sa-organization/categories/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-organization"))
                .route("sa-organization", r -> r.path("/api/sa-organization/organizations/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-organization"))
                .route("sa-organization", r -> r.path("/api/sa-organization/dishes/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-organization"))
                .route("sa-organization", r -> r.path("/api/sa-organization/rooms/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-organization"))

                /* sa-transaction-service */
                .route("sa-transaction-service", r -> r.path("/api/sa-transaction-service/public/**").uri("lb://sa-transaction-service"))
                .route("sa-transaction-service", r -> r.path("/api/sa-transaction-service/categories/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-transaction-service"))
                .route("sa-transaction-service", r -> r.path("/api/sa-transaction-service/sales/**").filters(f -> f.filter(authenticationFilter)).uri("lb://sa-transaction-service"))

                .build();
    }
}

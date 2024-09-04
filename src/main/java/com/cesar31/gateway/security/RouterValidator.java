package com.cesar31.gateway.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {

    public final static List<String> openApiEndpoints = List.of(
            "/public",
            "/api/sa-organization/public",
            "/api/sa-root/public",
            "api/sa-root/auth"
    );

    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints
            .stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));
}

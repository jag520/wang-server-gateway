package com.wang.server.gateway.config;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Configuration;

import com.wang.server.gateway.support.LocalCacheUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 动态路由
 */
@Configuration
public class RouteDefinitionRepositoryConfiguration implements RouteDefinitionRepository {

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(LocalCacheUtils.getDynamicRoutesDataCache().getDynamicRoutes().getRoutes());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}

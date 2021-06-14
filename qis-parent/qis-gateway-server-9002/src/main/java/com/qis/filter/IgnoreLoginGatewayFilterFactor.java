package com.qis.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author qishuo
 * @date 2021/5/30 10:51 下午
 */
@Component
public class IgnoreLoginGatewayFilterFactor extends AbstractGatewayFilterFactory<IgnoreLoginGatewayFilterFactor.Config> {
    public IgnoreLoginGatewayFilterFactor() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(LoginFilter.ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER, true);
        return chain.filter(exchange);
    }

    public static class Config {

    }

    @Override
    public String name() {
        return "IgnoreLogin";
    }
}

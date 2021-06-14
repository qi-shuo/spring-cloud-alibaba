package com.qis.filter;

import com.qis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author qishuo
 * @date 2021/5/30 8:15 下午
 */
@Slf4j
@Component
@RefreshScope
public class LoginFilter implements GlobalFilter, Ordered {
    public final static String ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER = "@ignoreLoginGlobal";

    @Reference(check = false)
    private IUserService userService;
    @Value("${redirect.url}")
    private String redirectUrl;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //跳过检测
        if (exchange.getAttribute(ATTRIBUTE_IGNORE_TEST_GLOBAL_FILTER) != null) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        HttpCookie cookie = cookies.getFirst("token");
        if (Objects.isNull(cookie)) {
            response.setStatusCode(HttpStatus.SEE_OTHER);
            log.info("未登录.重定向到登录页面:{}", redirectUrl);
            response.getHeaders().set("Location", redirectUrl);
            return exchange.getResponse().setComplete();
        } else {
            String value = cookie.getValue();
            String email = userService.userInfo(value);
            if (StringUtils.isEmpty(email)) {
                log.info("token不合法重新登录,重定向登录页面:{}", redirectUrl);
                response.setStatusCode(HttpStatus.SEE_OTHER);
                response.getHeaders().set("Location", redirectUrl);
                return exchange.getResponse().setComplete();
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 10;
    }
}

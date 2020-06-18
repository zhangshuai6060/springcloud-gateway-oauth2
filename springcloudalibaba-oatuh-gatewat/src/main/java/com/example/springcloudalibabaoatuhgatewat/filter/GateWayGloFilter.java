package com.example.springcloudalibabaoatuhgatewat.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 2 * @Author: ZhangShuai
 * 3 * @Date: 2020/6/18 10:11
 * 4
 */
@Slf4j
@Component
public class GateWayGloFilter implements GlobalFilter , Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return null;
    }


    @Override
    public int getOrder() {
        return -2;
    }
}

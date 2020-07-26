package cn.beanbang.microservice.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    //private static final Logger log = LoggerFactory.getLogger("accesslog");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        String addr = request.getLocalAddress().toString();
        String method = request.getMethod().toString();
        MultiValueMap<String, String> headers = request.getHeaders();
        MultiValueMap<String, String> queryParams = request.getQueryParams();

        log.info(new Date().toString());
        log.info("Address: {}", addr);
        log.info("Method:  {}", method);
        log.info("Headers: {}", headers);
        log.info("Params:  {}", queryParams);

        ServerHttpResponse response = exchange.getResponse();
        String code = response.getStatusCode().toString();
        MultiValueMap<String, String> responseHeaders = response.getHeaders();
        log.info(code);
        log.info(responseHeaders.toString());

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

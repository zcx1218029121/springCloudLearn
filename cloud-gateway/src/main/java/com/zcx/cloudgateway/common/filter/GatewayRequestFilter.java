package com.zcx.cloudgateway.common.filter;

import com.alibaba.fastjson.JSONObject;

import com.zcx.cloudgateway.properties.ZcxGatewayProperties;
import com.zcx.common.entity.MyResponse;
import com.zcx.common.entity.ZcxConstant;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.cloudgateway.common.filter
 * @ClassName: GatewayRequestFilter
 * @Author: loafer
 * @Description: 微服务防护全局过滤器
 * @Date: 2020/9/2 14:05
 * @Version: 1.0
 */
@Log4j2
@Component
public class GatewayRequestFilter implements GlobalFilter {

    @Autowired
    private ZcxGatewayProperties properties;




    private final AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 禁止客户端的访问资源逻辑
        Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }

        byte[] token = Base64Utils.encode((ZcxConstant.ZUUL_TOKEN_VALUE).getBytes());
        ServerHttpRequest build = request.mutate().header(ZcxConstant.ZUUL_TOKEN_HEADER, new String(token)).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

    /**
     * 检查当前资源是否允许外部访问
     *
     * @param request
     * @param response
     * @return
     */
    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            MyResponse febsResponse = new MyResponse().message("该URI不允许外部访问");
            return makeResponse(response, febsResponse);
        }
        return null;

    }

    private Mono<Void> makeResponse(ServerHttpResponse response, MyResponse febsResponse) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(febsResponse).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }
}

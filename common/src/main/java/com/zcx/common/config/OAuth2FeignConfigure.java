package com.zcx.common.config;

import com.zcx.common.entity.ZcxConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

import java.util.Base64;

/**
 * @author zcx
 */
public class OAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 为微服务添加 网关token
            String zuulToken = new String(Base64Utils.encode(ZcxConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(ZcxConstant.ZUUL_TOKEN_HEADER, zuulToken);

            // requestTemplate发起的请求添加  安全token
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}

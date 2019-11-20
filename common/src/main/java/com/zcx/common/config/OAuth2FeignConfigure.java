package com.zcx.common.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class OAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}

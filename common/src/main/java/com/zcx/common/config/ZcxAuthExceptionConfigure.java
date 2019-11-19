package com.zcx.common.config;


import com.zcx.common.handler.ZcxAccessDeniedHandler;
import com.zcx.common.handler.ZcxAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author loafer
 *
 *
 * 注解
 */
public class ZcxAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ZcxAccessDeniedHandler accessDeniedHandler() {
        return new ZcxAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public ZcxAuthExceptionEntryPoint authenticationEntryPoint() {
        return new ZcxAuthExceptionEntryPoint();
    }
}

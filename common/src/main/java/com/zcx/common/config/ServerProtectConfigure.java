package com.zcx.common.config;


import com.zcx.common.interceptor.ServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author loaferr
 * 注册拦截器 到 springMvc
 */
public class ServerProtectConfigure   implements WebMvcConfigurer {

    @Bean
    public ServerProtectInterceptor serverProtectInterceptor(){
        return new ServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor());
    }
}

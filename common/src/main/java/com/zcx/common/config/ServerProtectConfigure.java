package com.zcx.common.config;


import com.zcx.common.interceptor.ServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author loaferr
 * 注册拦截器 到 springMvc
 */
public class ServerProtectConfigure   implements WebMvcConfigurer {
    /**
     * springboot 选择性注入 当不存在PasswordEncoder的时候注入
     */
    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ServerProtectInterceptor serverProtectInterceptor(){
        return new ServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor());
    }
}

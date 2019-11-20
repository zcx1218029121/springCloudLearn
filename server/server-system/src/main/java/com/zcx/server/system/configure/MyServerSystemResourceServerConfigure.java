package com.zcx.server.system.configure;

import com.zcx.common.handler.ZcxAccessDeniedHandler;
import com.zcx.common.handler.ZcxAuthExceptionEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;

/**
 * @author loafer
 */
@Configuration
@EnableResourceServer
public class MyServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Resource
    private ZcxAuthExceptionEntryPoint zcxAuthExceptionEntryPoint;


    @Resource
    private ZcxAccessDeniedHandler zcxAccessDeniedHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(zcxAccessDeniedHandler).authenticationEntryPoint(zcxAuthExceptionEntryPoint);
    }
}

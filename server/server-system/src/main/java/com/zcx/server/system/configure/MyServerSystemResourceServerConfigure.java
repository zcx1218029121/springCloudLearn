package com.zcx.server.system.configure;

import com.zcx.common.handler.ZcxAccessDeniedHandler;
import com.zcx.common.handler.ZcxAuthExceptionEntryPoint;
import com.zcx.server.system.properties.ServerSystemProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;
import javax.annotation.Resources;

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

    @Resource
    private ServerSystemProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls)
                .permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(zcxAccessDeniedHandler).authenticationEntryPoint(zcxAuthExceptionEntryPoint);
    }
}

package com.zcx.clouldauth.configure;

import com.zcx.clouldauth.properties.AuthProperties;
import com.zcx.common.handler.ZcxAccessDeniedHandler;
import com.zcx.common.handler.ZcxAuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author zcx
 * 资源服务器授权认证
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic();
    }


}

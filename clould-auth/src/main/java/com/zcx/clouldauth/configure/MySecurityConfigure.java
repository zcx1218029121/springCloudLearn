package com.zcx.clouldauth.configure;

import com.zcx.clouldauth.filter.ValidateCodeFilter;
import com.zcx.clouldauth.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 *  Spring Cloud OAuth内部定义的获取令牌，刷新令牌的请求地址都是以/oauth/
 *  WebSecurityConfigurerAdapter 处理 oauth
 * @author zcx
 */
@Order(2)
@EnableWebSecurity
public class MySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Resource
    private MyUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Bean

    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();

    }

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        // 在  UsernamePasswordAuthenticationFilter 过滤器 前插入validateCodeFilter
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()

                .antMatchers("/oauth/**")

                .and()

                .authorizeRequests()

                .antMatchers("/oauth/**").authenticated()

                .and()

                .csrf().disable();


    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);

    }
}

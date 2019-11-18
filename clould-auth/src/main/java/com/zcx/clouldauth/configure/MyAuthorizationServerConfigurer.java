package com.zcx.clouldauth.configure;

import com.zcx.clouldauth.service.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author zcx
 * 验证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {
    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private MyUserDetailService myUserDetailService;

    @Resource

    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 服务权限验证配置

        clients.inMemory()

                .withClient("febs")

                .secret(passwordEncoder.encode("123456"))

                .authorizedGrantTypes("password", "refresh_token")

                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 配置springToken 的存储方式 默认springToken 是存储在内存中的
                // 配置用redis来储存 token
                .tokenStore(tokenStore())
                // 配置springSecurity 的认证方式
                .userDetailsService(myUserDetailService)
                /*
                 * ① AbstractAuthenticationProcessingFilter 基于收到的身份信息（用户名、密码）构造一个 Authentication 对象；
                 * ② AbstractAuthenticationProcessingFilter 将 Authentication 传递给 AuthenticationManager；
                 * ③ AuthenticationManager 有一个 AuthenticationProvider 列表，将 Authentication 委托给列表中的 AuthenticationProvider 处理认证请求；
                 * ④ AuthenticationProvider 依次对 Authentication 进行认证处理，如果认证不通过则抛出一个异常（注意对抛出的异常有类型要求）或直接返回 null，如果所有 AuthenticationProvider 都返回 null，则 AuthenticationManager 抛出 ProviderNotFoundException 异常；
                 * ⑤ 如果认证通过会返回一个填充完全的 Authentication，这个对象最终会被放入 SecurityContextHolder，后续用于授权功能。
                 *
                 */
                .authenticationManager(authenticationManager)
                // token 储存方式
                .tokenServices(defaultTokenServices());
    }

    @Bean

    public TokenStore tokenStore() {

        return new RedisTokenStore(redisConnectionFactory);

    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {

        DefaultTokenServices tokenServices = new DefaultTokenServices();

        tokenServices.setTokenStore(tokenStore());

        tokenServices.setSupportRefreshToken(true);

        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);

        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);

        return tokenServices;

    }
}

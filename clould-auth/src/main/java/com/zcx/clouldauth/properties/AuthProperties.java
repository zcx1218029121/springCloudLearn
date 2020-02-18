package com.zcx.clouldauth.properties;


import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author loafer
 * 自定义注解
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:cloud-auth.properties"})
@ConfigurationProperties(prefix = "zcx.auth")
public class AuthProperties {
    private ClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
    private String anonUrl;
    private ValidateCodeProperties code= new ValidateCodeProperties();
}

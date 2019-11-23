package com.zcx.server.test;

import com.zcx.common.annotation.CloudApplication;
import com.zcx.common.annotation.EnableServerProtect;
import com.zcx.common.annotation.EnableZcxAuthExceptionHandler;
import com.zcx.common.annotation.EnableZcxOAuth2FeignConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author zcx
 */

@CloudApplication
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerTestApplication.class, args);
    }

}

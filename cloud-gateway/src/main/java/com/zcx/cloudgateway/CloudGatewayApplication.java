package com.zcx.cloudgateway;

import com.zcx.common.annotation.EnableCrossDomainAllowed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author loafer
 * EnableCrossDomainAllowed  自定义注解允许跨域
 */
@EnableCrossDomainAllowed
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

}

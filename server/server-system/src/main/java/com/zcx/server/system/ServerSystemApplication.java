package com.zcx.server.system;

import com.zcx.common.annotation.EnableServerProtect;
import com.zcx.common.annotation.EnableZcxAuthExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zcx
 */
@EnableZcxAuthExceptionHandler
@EnableServerProtect
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.zcx.server.system.mapper")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ServerSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerSystemApplication.class, args);
    }

}

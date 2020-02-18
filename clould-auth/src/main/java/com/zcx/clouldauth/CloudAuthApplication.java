package com.zcx.clouldauth;

import com.zcx.common.annotation.EnableLettuceRedis;
import com.zcx.common.annotation.EnableServerProtect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author zcx
 * eurekaClient
 */
@EnableEurekaClient
@EnableServerProtect
@EnableLettuceRedis
@SpringBootApplication()
public class CloudAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

}

package com.zcx.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:server-system.properties"})
@ConfigurationProperties(prefix = "server.system")
public class ServerSystemProperties {
    /**
     * 跳过的url
     */
    private String anonUrl;
    /**
     * swagger 配置
     */
    private SwaggerProperties swaggerProperties = new SwaggerProperties();
}

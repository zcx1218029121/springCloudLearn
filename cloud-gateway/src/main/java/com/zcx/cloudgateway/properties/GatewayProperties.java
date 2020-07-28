package com.zcx.cloudgateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.cloudgateway.properties
 * @ClassName: GatewayProperties
 * @Author: loafer
 * @Description:
 * @Date: 2020/7/28 17:28
 * @Version: 1.0
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:zcx-gateway.properties"})
@ConfigurationProperties(prefix = "zcx.gateway")
public class GatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}

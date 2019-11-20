package com.zcx.server.test.service;


import com.zcx.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author loafer
 *
 * 微服务互相调用 接口
 */
@FeignClient(value = "service-system", contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {
    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}

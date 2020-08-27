package com.zcx.server.system.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loafer
 */
@Log4j2
@RestController
public class ZcxTestController {

    @GetMapping("hello")
    public String hello(String name) {

        log.info("/hello服务被调用");
        return "hello" + name;
    }
}

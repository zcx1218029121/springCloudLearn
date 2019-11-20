package com.zcx.server.system.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author loafer
 */
@RestController
public class ZcxTestController {

    @GetMapping("hello")
    public String hello(String name) {
        return "hello" + name;
    }
}

package com.zcx.server.test;

import com.zcx.server.test.service.IHelloService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
public class TestController {
    @Resource
    private IHelloService iHelloService;

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1() {
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2() {
        return "拥有'user:update'权限";
    }


    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return iHelloService.hello(name);
    }


    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}

package com.zcx.server.system.controller;

import com.zcx.common.entity.MyResponse;
import com.zcx.common.entity.router.VueRouter;
import com.zcx.common.entity.system.Menu;
import com.zcx.server.system.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.server.system.controller
 * @ClassName: MenuController
 * @Author: loafer
 * @Description:
 * @Date: 2020/8/18 11:52
 * @Version: 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @GetMapping("/{username}")
    public MyResponse getUserRouters(@NotBlank(message = "{required}") @PathVariable String username) {
        Map<String, Object> result = new HashMap<>();
        // 构建用户路由对象
        List<VueRouter<Menu>> userRouters = this.menuService.getUserRouters(username);
        // 获取用户权限信息
        Set<String> userPermissions = this.menuService.findUserPermissions(username);
        // 组装数据
        result.put("routes", userRouters);
        result.put("permissions", userPermissions);
        return new MyResponse().data(result);
    }
}

package com.zcx.server.system.service.impl;

import com.zcx.common.entity.router.RouterMeta;
import com.zcx.common.entity.router.VueRouter;
import com.zcx.common.entity.system.Menu;
import com.zcx.common.util.TreeUtil;
import com.zcx.server.system.mapper.MenuMapper;
import com.zcx.server.system.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.server.system.service.impl
 * @ClassName: MenuServiceImpl
 * @Author: loafer
 * @Description:
 * @Date: 2020/8/18 11:49
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Resource
    MenuMapper menuMapper;

    @Override
    public Set<String> findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.toSet());
    }

    @Override
    public List<VueRouter<Menu>> getUserRouters(String username) {
        List<VueRouter<Menu>> routes = new ArrayList<>();
        List<Menu> menus = menuMapper.findUserMenus(username);
        menus.forEach(menu -> {
            VueRouter<Menu> route = new VueRouter<>();
            route.setId(menu.getMenuId().toString());
            route.setParentId(menu.getParentId().toString());
            route.setPath(menu.getPath());
            route.setComponent(menu.getComponent());
            route.setName(menu.getMenuName());
            route.setMeta(new RouterMeta(menu.getMenuName(), menu.getIcon()));
            routes.add(route);
        });
        return TreeUtil.buildVueRouter(routes);
    }
}

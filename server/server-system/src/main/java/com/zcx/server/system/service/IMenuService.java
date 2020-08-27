package com.zcx.server.system.service;

import com.zcx.common.entity.router.VueRouter;
import com.zcx.common.entity.system.Menu;

import java.util.List;
import java.util.Set;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.server.system.service
 * @ClassName: IMenuService
 * @Author: loafer
 * @Description:
 * @Date: 2020/8/18 11:48
 * @Version: 1.0
 */
public interface IMenuService {
    /**
     * 通过用户名查询用户权限信息
     *
     * @param username 用户名
     * @return 权限信息
     */
    Set<String> findUserPermissions(String username);

    /**
     * 通过用户名创建对应的 Vue路由信息
     *
     * @param username 用户名
     * @return 路由信息
     */
    List<VueRouter<Menu>> getUserRouters(String username);
}

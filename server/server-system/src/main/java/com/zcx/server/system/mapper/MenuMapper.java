package com.zcx.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zcx.common.entity.router.VueRouter;
import com.zcx.common.entity.system.Menu;

import java.util.List;

/**
 * @ProjectName: cloud-gateway
 * @Package: com.zcx.server.system.mapper
 * @ClassName: MenuMapper
 * @Author: loafer
 * @Description:
 * @Date: 2020/8/18 11:23
 * @Version: 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 通过用户名查询权限信息
     *
     * @param username 用户名称
     * @return 权限信息
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 通过用户名查询菜单信息
     *
     * @param username 用户名
     * @return 菜单信息
     */
    List<Menu> findUserMenus(String username);
}

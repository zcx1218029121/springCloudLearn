package com.zcx.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zcx.common.entity.system.UserRole;

public interface IUserRoleService extends IService<UserRole> {
    /**
     * 更加角色id 删除角色
     * @param roleIds
     */
    void deleteUserRolesByRoleId(String[] roleIds);

    /**
     * 根据用户id删除对应的用户角色
     * @param userIds
     */
    void deleteUserRolesByUserId(String[] userIds);
}

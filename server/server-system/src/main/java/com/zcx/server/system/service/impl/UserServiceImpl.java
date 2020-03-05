package com.zcx.server.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcx.common.entity.QueryRequest;
import com.zcx.common.entity.system.SystemUser;
import com.zcx.server.system.mapper.UserMapper;
import com.zcx.server.system.service.IUserRoleService;
import com.zcx.server.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zcx
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<SystemUser> findUserDetail(SystemUser user, QueryRequest request) {
        return null;
    }

    @Override
    public void createUser(SystemUser user) {

    }

    @Override
    public void updateUser(SystemUser user) {

    }

    @Override
    public void deleteUsers(String[] userIds) {

    }
}

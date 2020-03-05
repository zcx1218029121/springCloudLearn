package com.zcx.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zcx.common.entity.system.UserRole;
import com.zcx.server.system.mapper.UserRoleMapper;
import com.zcx.server.system.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author zcx
 */
@Service("userRoleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Transactional(rollbackFor =Exception.class )
    @Override
    public void deleteUserRolesByRoleId(String[] roleIds) {
        Arrays.stream(roleIds).forEach(roleId -> baseMapper.deleteByRoleId(Long.valueOf(roleId)));

    }
    @Transactional(rollbackFor =Exception.class )
    @Override
        public void deleteUserRolesByUserId(String[] userIds) {
        Arrays.stream(userIds).forEach(userId->baseMapper.deleteByUserId(Long.valueOf(userId)));

    }
}

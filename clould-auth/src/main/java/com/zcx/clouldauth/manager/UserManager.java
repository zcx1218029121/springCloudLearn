package com.zcx.clouldauth.manager;

import com.zcx.common.entity.system.Menu;
import com.zcx.common.entity.system.SystemUser;
import com.zcx.clouldauth.mapper.MenuMapper;
import com.zcx.clouldauth.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager  {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MenuMapper menuMapper;

    public SystemUser findUserByName(String userName){
        return userMapper.findByUserName(userName);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);
        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }

}

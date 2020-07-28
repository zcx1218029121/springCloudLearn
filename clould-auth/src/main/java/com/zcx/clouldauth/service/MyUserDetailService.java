package com.zcx.clouldauth.service;

import com.zcx.common.entity.AuthUser;
import com.zcx.common.entity.system.SystemUser;
import com.zcx.clouldauth.manager.UserManager;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zcx
 */
@Service
public class MyUserDetailService implements UserDetailsService {


    @Resource
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = userManager.findUserByName(username);
        if (systemUser != null) {
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus())) {
                notLocked = true;
            }
            AuthUser authUser = new AuthUser(systemUser.getUsername(), systemUser.getPassword(), true, true, true, notLocked,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));

            BeanUtils.copyProperties(systemUser, authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }

    }

}

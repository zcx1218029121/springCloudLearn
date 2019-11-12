package com.zcx.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zcx
 */
@Data
public class AuthUser implements Serializable {

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
}

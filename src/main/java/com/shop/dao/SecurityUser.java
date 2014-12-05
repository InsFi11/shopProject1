package com.shop.dao;


import com.shop.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * Created by InF on 27.06.2014.
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SecurityUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,  Collection<? extends GrantedAuthority> authorities) throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
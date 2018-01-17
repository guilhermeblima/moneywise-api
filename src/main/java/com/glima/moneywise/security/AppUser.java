package com.glima.moneywise.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Guilherme on 18/01/2018.
 */
@Getter
public class AppUser extends User {

    private com.glima.moneywise.model.User user;

    public AppUser(com.glima.moneywise.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
}

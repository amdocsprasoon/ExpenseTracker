package org.sebprojects.expensetracker.services;

import org.sebprojects.expensetracker.entities.Roles;
import org.sebprojects.expensetracker.entities.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {
    private String username;
    private String password;

    Collection<? extends GrantedAuthority > authorities;

    public CustomUserDetails(UserInfo userInfo) {
        this.username = userInfo.getUserName();
        this.password = userInfo.getPassword();

        List<GrantedAuthority> auths = new ArrayList<>();

        for(Roles role : userInfo.getRoles()) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        this.authorities = auths;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

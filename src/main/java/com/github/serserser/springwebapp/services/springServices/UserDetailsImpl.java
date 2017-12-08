package com.github.serserser.springwebapp.services.springServices;

import com.github.serserser.springwebapp.domain.Privilege;
import com.github.serserser.springwebapp.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl extends Authorized implements UserDetails {

    private User user;

    private Set<? extends GrantedAuthority> privileges;

    public UserDetailsImpl(User user) {
        this.user = user;
        this.privileges = mapToGrantedAuthority(user.getPrivileges());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(privileges);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

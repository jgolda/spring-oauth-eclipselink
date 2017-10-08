package com.github.serserser.springwebapp.services.springUsers;

import com.github.serserser.springwebapp.domain.Privilege;
import com.github.serserser.springwebapp.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private User user;

    private Set<? extends GrantedAuthority> privileges;

    public UserDetailsImpl(User user, Map<String, Privilege> privileges) {
        this.user = user;
        this.privileges = mapToGrantedAuthority(privileges);
    }

    private Set<SimpleGrantedAuthority> mapToGrantedAuthority(Map<String, Privilege> privileges) {
        return privileges.values().stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getCode()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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

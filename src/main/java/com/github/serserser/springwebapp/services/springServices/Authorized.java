package com.github.serserser.springwebapp.services.springServices;

import com.github.serserser.springwebapp.domain.Privilege;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Authorized {

    public abstract Collection<GrantedAuthority> getAuthorities();

    protected Set<GrantedAuthority> mapToGrantedAuthority(Map<String, Privilege> privileges) {
        return privileges.values().stream()
                .map(privilege -> new SimpleGrantedAuthority(privilege.getCode()))
                .collect(Collectors.toSet());
    }
}

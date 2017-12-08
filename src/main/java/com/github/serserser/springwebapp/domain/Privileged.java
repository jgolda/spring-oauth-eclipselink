package com.github.serserser.springwebapp.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Privileged {

    public abstract Collection<Role> getRoles();

    public Map<String, Privilege> getPrivileges() {
        return getRoles().stream()
                .map(Role::getPrivileges)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (first, last) -> last));
    }
}

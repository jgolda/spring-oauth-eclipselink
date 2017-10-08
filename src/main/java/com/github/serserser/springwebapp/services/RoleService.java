package com.github.serserser.springwebapp.services;

import com.github.serserser.springwebapp.domain.Privilege;
import com.github.serserser.springwebapp.domain.Role;
import com.github.serserser.springwebapp.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        roleRepository.findAll().forEach(result::add);
        return result;
    }

    public Map<String, Role> findByCode(List<String> roleCodes) {
        return roleRepository.findByCodeIn(roleCodes).stream()
                .collect(Collectors.toMap(role -> role.getCode(), role -> role));
    }
}

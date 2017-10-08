package com.github.serserser.springwebapp.services;

import com.github.serserser.springwebapp.domain.Privilege;
import com.github.serserser.springwebapp.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrivilegeService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> findAll() {
        List<Privilege> result = new ArrayList<>();
        privilegeRepository.findAll().forEach(result::add);
        return result;
    }

    public Map<String, Privilege> findByCode(Set<String> privilegeCodes) {
        return privilegeRepository.findByCodeIn(privilegeCodes).stream()
                .collect(Collectors.toMap(privilege -> privilege.getCode(), privilege -> privilege));
    }
}

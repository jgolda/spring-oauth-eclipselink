package com.github.serserser.springwebapp.repositories;

import com.github.serserser.springwebapp.domain.Privilege;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    List<Privilege> findByCodeIn(Set<String> codes);
}

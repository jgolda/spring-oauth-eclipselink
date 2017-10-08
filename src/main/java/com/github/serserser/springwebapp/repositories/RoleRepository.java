package com.github.serserser.springwebapp.repositories;

import com.github.serserser.springwebapp.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("select r from Role r where r.code in :codes")
    List<Role> findByCodeIn(@Param("codes") List<String> codes);
}

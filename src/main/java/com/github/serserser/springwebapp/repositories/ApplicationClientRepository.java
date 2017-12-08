package com.github.serserser.springwebapp.repositories;

import com.github.serserser.springwebapp.domain.ApplicationClient;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationClientRepository extends CrudRepository<ApplicationClient, Long>{

    ApplicationClient findByApplicationClientId(String applicationClientId);
}

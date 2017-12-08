package com.github.serserser.springwebapp.services;

import com.github.serserser.springwebapp.domain.ApplicationClient;
import com.github.serserser.springwebapp.repositories.ApplicationClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicationClientDetailsService {

    @Autowired
    private ApplicationClientRepository repository;

    public Set<ApplicationClient> getAllApplicationClients() {
        Set<ApplicationClient> result = new HashSet<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    public Optional<ApplicationClient> findByClientId(String clientId) {
        return Optional.ofNullable(repository.findByApplicationClientId(clientId));
    }
}

package com.github.serserser.springwebapp.services.springServices;

import com.github.serserser.springwebapp.services.ApplicationClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationClientsDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ApplicationClientDetailsService service;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return service.findByClientId(clientId)
                .map(ApplicationClientDetailsImpl::new)
                .orElseThrow(() -> new ClientRegistrationException("No client with id: " + clientId + " registered"));
    }
}

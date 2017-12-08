package com.github.serserser.springwebapp.rest.services;

import com.github.serserser.springwebapp.domain.ApplicationClient;
import com.github.serserser.springwebapp.services.ApplicationClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Component
@Path("/clients")
public class ApplicationClientComponent {

    @Autowired
    private ApplicationClientDetailsService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<ApplicationClient> getClients() {
        return service.getAllApplicationClients();
    }
}

package com.github.serserser.springwebapp.rest.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/privilege")
public class PrivilegeComponent {

    @GET
    @PreAuthorize("hasAuthority('PRIVILEGE_VIEW')")
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        return "jestem smokiem!";
    }
}

package com.github.serserser.springwebapp.rest.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/privilege")
public class PrivilegeComponent {

    @GET
    public String get() {
        return "jestem smokiem!";
    }
}

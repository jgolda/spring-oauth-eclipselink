package com.github.serserser.springwebapp.rest.filters;

import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Component
public class DefaultMediaTypeFilter implements ContainerRequestFilter {

    public static final String ACCEPT_HEADER_NAME = "Accept";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
        if (!headers.containsKey(ACCEPT_HEADER_NAME)) {
            headers.add(ACCEPT_HEADER_NAME, MediaType.APPLICATION_JSON);
        }
    }
}

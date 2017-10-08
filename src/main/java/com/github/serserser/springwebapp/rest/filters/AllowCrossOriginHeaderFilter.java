package com.github.serserser.springwebapp.rest.filters;

import org.springframework.stereotype.Component;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Component
public class AllowCrossOriginHeaderFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        addHeader(containerResponseContext, "Access-Control-Allow-Origin", "*");
        addHeader(containerResponseContext, "Access-Control-Allow-Headers", "origin, content-type, accept, authorization, Access-Control-Allow-Origin");
        addHeader(containerResponseContext, "Access-Control-Allow-Credentials", "true");
        addHeader(containerResponseContext, "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        addHeader(containerResponseContext, "Access-Control-Max-Age", "1209600");
    }

    private void addHeader(ContainerResponseContext containerResponseContext, String headerName, String headerValue) {
        containerResponseContext.getHeaders().add(headerName, headerValue);
    }
}

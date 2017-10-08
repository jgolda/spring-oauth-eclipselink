package com.github.serserser.springwebapp.rest.config;

import com.github.serserser.springwebapp.rest.filters.AllowCrossOriginHeaderFilter;
import com.github.serserser.springwebapp.rest.services.PrivilegeComponent;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

@ApplicationPath("/api")
public class ApplicationInitializer extends ResourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    public ApplicationInitializer(@Context ServletContext context) {
        logger.info("Initializing the rest api");
        WebApplicationContext webAppContext = WebApplicationContextUtils.getWebApplicationContext(context);

        register(webAppContext.getBean(PrivilegeComponent.class));
        register(webAppContext.getBean(AllowCrossOriginHeaderFilter.class));
        logger.info("Finished initializing the rest api");
    }
}

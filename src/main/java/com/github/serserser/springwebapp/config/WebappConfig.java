package com.github.serserser.springwebapp.config;

import com.github.serserser.springwebapp.config.oauth.AuthorizationServerConfiguration;
import com.github.serserser.springwebapp.config.oauth.JwtTokenConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class WebappConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = getContext();
        context.setServletContext(servletContext);

        servletContext.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain", context);
        filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
        servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocations(
                DatabaseConfig.class.getCanonicalName(),
                SpringSecurityConfig.class.getCanonicalName(),
                WebappConfig.class.getCanonicalName(),
                AuthorizationServerConfiguration.class.getCanonicalName(),
                JwtTokenConfiguration.class.getCanonicalName(),
                MvcConfig.class.getCanonicalName()
        );

        return context;
    }
}

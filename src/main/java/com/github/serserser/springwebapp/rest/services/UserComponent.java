package com.github.serserser.springwebapp.rest.services;

import com.github.serserser.springwebapp.domain.User;
import com.github.serserser.springwebapp.exceptions.NoSuchUserException;
import com.github.serserser.springwebapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserComponent {

    private static final Logger logger = LoggerFactory.getLogger(UserComponent.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager em;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    public User updateNames(
            @QueryParam("login") String login,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName")String lastName) {
        User user = userService.findByLogin(login)
                .orElseThrow(() -> new NoSuchUserException("No user with login: " + login + " found"));
        em.detach(user);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userService.modifyUser(user);
    }
}

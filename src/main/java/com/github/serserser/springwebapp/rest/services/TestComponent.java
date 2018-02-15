package com.github.serserser.springwebapp.rest.services;

import com.github.serserser.springwebapp.rest.dto.Test;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@Component
@Path("/tests")
public class TestComponent {

    @GET
    public List<Test> getTestsOfUser(@QueryParam("userId") Long userId) {
        return new ArrayList<>();
    }
}

package com.github.serserser.springwebapp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void generatePasswd() {
        String hashed = new StandardPasswordEncoder().encode("secret");
        System.out.println(hashed);
        boolean matches = new StandardPasswordEncoder().matches("secret", hashed);
        System.out.println(matches ? "matches" : "not matches");
    }
}
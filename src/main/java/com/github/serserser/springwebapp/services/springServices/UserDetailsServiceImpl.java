package com.github.serserser.springwebapp.services.springServices;

import com.github.serserser.springwebapp.domain.User;
import com.github.serserser.springwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userService.findByLogin(login)
                .map(user -> new UserDetailsImpl(user))
                .orElseThrow(() -> new UsernameNotFoundException("No user with login: " + login));
    }
}

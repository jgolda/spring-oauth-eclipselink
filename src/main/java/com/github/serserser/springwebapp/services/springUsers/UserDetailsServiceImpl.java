package com.github.serserser.springwebapp.services.springUsers;

import com.github.serserser.springwebapp.domain.User;
import com.github.serserser.springwebapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByLogin(login);

        if ( ! optionalUser.isPresent() ) {
            throw new UsernameNotFoundException("No such user " + login);
        }

        User user = optionalUser.get();

        return new UserDetailsImpl(user, user.getPrivileges());
    }
}

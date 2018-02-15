package com.github.serserser.springwebapp.services;

import com.github.serserser.springwebapp.domain.Role;
import com.github.serserser.springwebapp.domain.User;
import com.github.serserser.springwebapp.exceptions.InvalidRoleException;
import com.github.serserser.springwebapp.exceptions.NoSuchUserException;
import com.github.serserser.springwebapp.exceptions.SystemException;
import com.github.serserser.springwebapp.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EntityManager em;

    public User createUser(@Valid User user) {
        String plainPassword = user.getPlainPassword();

        if ( StringUtils.isNotEmpty(plainPassword) ) {
            user.setPassword(encoder.encode(plainPassword));
        }

        return userRepository.save(user);
    }

    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    public Optional<User> findByLogin(String login) {
        List<User> users = userRepository.findByLogin(login, new PageRequest(0, 1));

        if ( users.size() == 1 ) {
            return Optional.of(users.get(0));
        } else {
            return Optional.empty();
        }
    }

    public User findById(Long id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            throw new NoSuchUserException("Couldn't find user by id: " + id);
        }

        return user;
    }



    public User modifyPrivileges(User user, List<String> assignedRolesCodes) throws InvalidRoleException {
        user.getRoles().clear();
        return addPrivileges(user, assignedRolesCodes);
    }

    public User addPrivileges(User user, List<String> assignedRolesCodes) throws InvalidRoleException {
        Map<String, Role> roles = roleService.findByCode(assignedRolesCodes);

        if (!roles.keySet().containsAll(assignedRolesCodes)) {
            throw new InvalidRoleException("Chosen privilege does not exist");
        }

        Collection<Role> assignedRoles = roles.values();
        user.getRoles().addAll(assignedRoles);
        return userRepository.save(user);
    }

    @Transactional
    public User modifyUser(@Valid User user) {
//        User result = userRepository.save(user);
        User result = em.merge(user);
        logger.info("after saving item");
        return result;
    }
}

package com.github.serserser.springwebapp.repositories;

import com.github.serserser.springwebapp.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLogin(String login, Pageable paging);

    User findByLogin(String login);
}

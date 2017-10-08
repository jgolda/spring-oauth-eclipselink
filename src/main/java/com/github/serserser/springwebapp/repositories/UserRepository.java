package com.github.serserser.springwebapp.repositories;

import com.github.serserser.springwebapp.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByLogin(String login, Pageable paging);
}

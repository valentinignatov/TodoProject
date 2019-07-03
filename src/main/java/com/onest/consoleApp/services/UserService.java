package com.onest.consoleApp.services;

import com.onest.consoleApp.models.User;
import com.onest.consoleApp.models.UserWithNrOfTodos;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    Optional<User> findByUsername(String username);

    User findById(Long userId);

    List<User> findAll();

    UserWithNrOfTodos showNrOfTodosForUser();

}

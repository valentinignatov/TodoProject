package com.onest.consoleApp.repositories;

import com.onest.consoleApp.models.User;
import com.onest.consoleApp.models.UserWithNrOfTodos;

import java.util.ArrayList;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);

    ArrayList<User> findAll();

    UserWithNrOfTodos showNrOfTodosForUser();
}

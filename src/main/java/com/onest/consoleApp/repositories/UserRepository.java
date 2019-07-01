package com.onest.consoleApp.repositories;

import com.onest.consoleApp.models.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);
}

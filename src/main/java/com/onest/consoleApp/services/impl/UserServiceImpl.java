package com.onest.consoleApp.services.impl;

import com.onest.consoleApp.models.User;
import com.onest.consoleApp.models.UserWithNrOfTodos;
import com.onest.consoleApp.repositories.UserRepository;
import com.onest.consoleApp.repositories.impl.UserRepositoryImpl;
import com.onest.consoleApp.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long userId) {
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserWithNrOfTodos showNrOfTodosForUser() {

        return userRepository.showNrOfTodosForUser();
    }

}

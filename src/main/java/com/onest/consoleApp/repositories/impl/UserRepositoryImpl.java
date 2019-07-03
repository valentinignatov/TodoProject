package com.onest.consoleApp.repositories.impl;

import com.onest.consoleApp.models.User;
import com.onest.consoleApp.models.UserWithNrOfTodos;
import com.onest.consoleApp.repositories.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private Connection connection;

    public UserRepositoryImpl() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/todos",
                    "postgres",
                    "558226");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User user) {

        try {

            String sql = "insert into users (user_name) values (?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

                stmt.setString(1, user.getUsername());
                stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {

        try {

            String sql = "select id, user_name from users where user_name = ?;";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("user_name"));
                return Optional.of(user);
            }

            return Optional.empty();

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public ArrayList<User> findAll() {

        String sql = "select id, user_name from users;";

        ArrayList<User> temp = new ArrayList<>();

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                User user =  new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("user_name"));
                temp.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public UserWithNrOfTodos showNrOfTodosForUser() {

        UserWithNrOfTodos user = new UserWithNrOfTodos();

        String sql = "";
        return null;
    }
}

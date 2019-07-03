package com.onest.consoleApp.repositories.impl;

import com.onest.consoleApp.models.Todo;
import com.onest.consoleApp.repositories.TodoRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepositoryImpl implements TodoRepository {

    private Connection connection;

    private Statement stmt = null;

    public TodoRepositoryImpl() {
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
    public Todo save(Todo todo) {

        Todo savedTodo = new Todo();

        try {

            String sql = "insert into todos (user_id, text) values (?, ?);";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, todo.getUserId().intValue());
            stmt.setString(2, todo.getText());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Todo> findByName(String name) {

        try {

            String sql = "select text from todos where text= ?;";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setUserId(rs.getLong("user_id"));
            todo.setText(rs.getString("text"));
            todo.setCreatedOn(rs.getDate("date_created"));

            return Optional.of(todo);
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public List<Todo> findAllByUsername(String username) {

        ArrayList<Todo> temp = new ArrayList<Todo>();

        try {

            String sql = "select todos.id, todos.user_id, todos.text, todos.date_created, todos.date_updated" +
                    " from todos " +
                    "left join users on users.id = todos.user_id " +
                    "where users.user_name = ?;";

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Todo todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setUserId(rs.getLong("user_id"));
                todo.setText(rs.getString("text"));
                temp.add(todo);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }

    public List<Todo> findByUserId(Long id){

        ArrayList<Todo> temp = new ArrayList<>();

        String sql = "select id, user_id, text from todos where user_id = ?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Todo foundTodo = new Todo();
                foundTodo.setId(rs.getLong("id"));
                foundTodo.setUserId(rs.getLong("user_id"));
                foundTodo.setText(rs.getString("text"));

                temp.add(foundTodo);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public List<Todo> findByTagId(Long userId, Long tagId) {

        ArrayList<Todo> temp = new ArrayList<>();

        String sql = "select text from todos, tags, todos_to_tags " +
                "where todos.id = todos_to_tags.todo_id " +
                "and todos_to_tags.tag_id = tags.id " +
                "and todos.user_id = ? and tags.id = ?;";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, userId);
            stmt.setLong(2, tagId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Todo todo = new Todo();
                todo.setText(rs.getString("text"));
                temp.add(todo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temp;
    }

    @Override
    public List<Todo> findByIntroducedText(String text) {

        ArrayList<Todo> temp = new ArrayList<>();

        String sql = "select text from todos where text like ?;";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + text + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){

                Todo todo = new Todo();
                todo.setText(rs.getString("text"));
                temp.add(todo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    @Override
    public Todo findByTodoId(Long id) {
        Todo todo = new Todo();

        String sql = "select text from todos where id = ?";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1,id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                todo.setText(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    @Override
    public Todo alterTodoById(String newTodo, Long id) {
        String sql = "update todos set text = ? where id = ?;";

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, newTodo);
            stmt.setLong(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long numberOfTodos(Long userId) {

        String sql = "SELECT COUNT (text) FROM todos where user_id = ?;";

        Long nr = null;

        try {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setLong(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                nr = rs.getLong(1);
            }

            //stmt.setLong();

            //stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nr;
    }

}

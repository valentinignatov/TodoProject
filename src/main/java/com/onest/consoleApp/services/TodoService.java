package com.onest.consoleApp.services;

import com.onest.consoleApp.models.Todo;
import com.onest.consoleApp.models.TodoDetailed;

import java.util.List;
import java.util.Optional;

public interface TodoService {

    Todo save(Todo todo);

    Optional<Todo> findByName(String name);

    List<Todo> findByUserId(Long id);

    List<Todo> findAll();

    List<TodoDetailed> findAllByUsername(String username);

    List<Todo> findByTagId(Long userId, Long tagId);

    List<Todo> findByIntroducedText(String text);

    Todo findByTodoId(Long id);

    Todo alterTodoById(String newTodo, Long id);

    Long numberOfTodos(Long userId);
}

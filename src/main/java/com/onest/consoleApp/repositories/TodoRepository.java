package com.onest.consoleApp.repositories;

import com.onest.consoleApp.models.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    Todo save(Todo todo);

    Optional<Todo> findByName(String name);

    List<Todo> findAllByUsername(String user);

    List<Todo> findByUserId(Long id);

    List<Todo> findByTagId(Long userId, Long tagId);

    List<Todo> findByIntroducedText(String text);

    Todo findByTodoId(Long id);

    Todo alterTodoById(String newTodo, Long id);
}

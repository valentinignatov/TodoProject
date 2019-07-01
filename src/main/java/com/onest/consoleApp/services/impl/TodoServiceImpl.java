package com.onest.consoleApp.services.impl;

import com.onest.consoleApp.models.Todo;
import com.onest.consoleApp.models.TodoDetailed;
import com.onest.consoleApp.services.TagService;
import com.onest.consoleApp.services.TodoService;
import com.onest.consoleApp.repositories.impl.TodoRepositoryImpl;
import com.onest.consoleApp.repositories.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private TagService tagService;

    public TodoServiceImpl() {
        this.todoRepository = new TodoRepositoryImpl();
        this.tagService = new TagServiceImpl();
    }

    @Override
    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> findByName(String name) {
        return todoRepository.findByName(name);
    }

    @Override
    public List<Todo> findByUserId(Long id) {

        return todoRepository.findByUserId(id);
    }

    @Override
    public List<Todo> findAll() {
        return null;
    }

    @Override
    public List<TodoDetailed> findAllByUsername(String user){
        return todoRepository
                .findAllByUsername(user)
                .stream()
                .map(todo -> new TodoDetailed(todo.getId(), todo.getText(), tagService.findAllByTodoId(todo.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByTagId(Long userId, Long tagId) {

        return todoRepository.findByTagId(userId, tagId);
    }

}

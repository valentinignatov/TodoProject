package com.onest.consoleApp.services;

import com.onest.consoleApp.models.Tag;
import com.onest.consoleApp.models.Todo;

import java.util.ArrayList;
import java.util.List;

public interface TagService {

    List<Tag> findAllByTodoId(Long todoId);

    Tag addTagByTodoId(long todoId, long tagId);

    ArrayList<Tag> findAll();

}

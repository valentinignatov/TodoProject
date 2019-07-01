package com.onest.consoleApp.repositories;

import com.onest.consoleApp.models.Tag;

import java.util.ArrayList;
import java.util.List;

public interface TagRepository {

    List<Tag> findAllByTodoId(Long todoId);

    void addTagByTodoId(long todoId, long tagId);

    ArrayList<Tag> findAll();
}

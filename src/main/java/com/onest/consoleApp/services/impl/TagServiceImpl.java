package com.onest.consoleApp.services.impl;

import com.onest.consoleApp.models.Tag;
import com.onest.consoleApp.repositories.TagRepository;
import com.onest.consoleApp.repositories.impl.TagRepositoryImpl;
import com.onest.consoleApp.services.TagService;

import java.util.ArrayList;
import java.util.List;

public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl() {
        this.tagRepository = new TagRepositoryImpl();
    }

    @Override
    public List<Tag> findAllByTodoId(Long todoId) {
        return tagRepository.findAllByTodoId(todoId);
    }

    @Override
    public Tag addTagByTodoId(long todoId, long tagId) {

        tagRepository.addTagByTodoId(todoId, tagId);

        return null;
    }

    @Override
    public ArrayList<Tag> findAll() {

        return tagRepository.findAll();
    }
}

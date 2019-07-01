package com.onest.consoleApp.models;

import java.util.Arrays;
import java.util.List;

public class TodoDetailed {

    private Long id;

    private String text;

    private List<Tag> tags;

    public TodoDetailed(Long id, String text, List<Tag> tags) {
        this.id = id;
        this.text = text;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return this.getId() + " | " + this.getText() + " | \t\t\t tags: " + Arrays.toString(this.getTags().toArray());
    }
}

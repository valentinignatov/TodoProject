package com.onest.consoleApp.models;

public class Tag {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag() {
        this.id = null;
        this.name = "";
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getName();
    }
}

package com.onest.consoleApp.models;

public class User {

    private Long id;

    private String username;

    public User() {
        this.id = null;
        this.username = "";
    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.onest.consoleApp.models;

public class UserWithNrOfTodos {

    Long id;

    String username;

    public UserWithNrOfTodos() {}

    public UserWithNrOfTodos(Long id, String username, int nrOfTodos) {
        this.id = id;
        this.username = username;
        this.nrOfTodos = nrOfTodos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNrOfTodos() {
        return nrOfTodos;
    }

    public void setNrOfTodos(int nrOfTodos) {
        this.nrOfTodos = nrOfTodos;
    }

    int nrOfTodos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

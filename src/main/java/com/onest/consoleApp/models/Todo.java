package com.onest.consoleApp.models;

import java.util.Date;

public class Todo {

    private Long id;

    private Long userId;

    private String text;

    private Date createdOn;

    private Date updatedOn;

    public Todo(Long id, String text){
        this.id = id;
        this.text = text;
    }

    public Todo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getText() + " " + this.getCreatedOn();
    }
}
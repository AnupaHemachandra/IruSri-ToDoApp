package com.irusri.TodoApp.dto.response;

public class ResponseUserDTO {
    private int id;
    String username;

    public ResponseUserDTO() {
    }

    public ResponseUserDTO(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

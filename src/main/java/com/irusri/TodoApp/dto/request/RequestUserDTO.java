package com.irusri.TodoApp.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestUserDTO {
    private String username;
    private String password;
    private String newPassword;
    private String confirmNewPassword;

    public RequestUserDTO() {
    }

    public RequestUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public RequestUserDTO(String username, String password, String newPassword, String confirmNewPassword) {
        this.username = username;
        this.password = password;
        this.newPassword = newPassword;
        this.confirmNewPassword = confirmNewPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
}

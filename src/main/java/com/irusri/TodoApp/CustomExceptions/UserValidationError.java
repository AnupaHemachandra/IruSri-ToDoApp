package com.irusri.TodoApp.CustomExceptions;

public class UserValidationError extends RuntimeException{
    public UserValidationError() {
    }

    public UserValidationError(String message) {
        super(message);
    }
}

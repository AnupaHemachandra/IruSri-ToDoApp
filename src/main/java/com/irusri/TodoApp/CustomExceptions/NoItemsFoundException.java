package com.irusri.TodoApp.CustomExceptions;

public class NoItemsFoundException extends RuntimeException{
    public NoItemsFoundException() {
    }

    public NoItemsFoundException(String message) {
        super(message);
    }
}

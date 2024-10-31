package com.irusri.TodoApp.CustomExceptions;

public class NoSuchCustomerExistsException extends RuntimeException {

    public NoSuchCustomerExistsException() {
    }

    public NoSuchCustomerExistsException(String message) {
        super(message);
    }
}

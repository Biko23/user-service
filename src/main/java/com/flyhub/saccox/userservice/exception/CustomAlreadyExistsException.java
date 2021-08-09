package com.flyhub.saccox.userservice.exception;

public class CustomAlreadyExistsException extends RuntimeException {

    private String message;

    public CustomAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

}

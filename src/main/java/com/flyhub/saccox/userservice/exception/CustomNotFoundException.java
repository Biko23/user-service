package com.flyhub.saccox.userservice.exception;

public class CustomNotFoundException extends RuntimeException {

    private String message;

    public CustomNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}

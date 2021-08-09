package com.flyhub.saccox.userservice.exception;

public class CustomNotAuthorisedException extends RuntimeException {

    private String message;

    public CustomNotAuthorisedException(String message) {
        super(message);
        this.message = message;
    }

}

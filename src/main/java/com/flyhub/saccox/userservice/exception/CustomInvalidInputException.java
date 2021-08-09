package com.flyhub.saccox.userservice.exception;

public class CustomInvalidInputException extends RuntimeException {

    private String message;

    public CustomInvalidInputException(String message) {
        super(message);
        this.message = message;
    }

}

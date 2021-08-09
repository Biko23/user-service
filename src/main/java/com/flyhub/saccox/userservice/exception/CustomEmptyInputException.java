package com.flyhub.saccox.userservice.exception;

public class CustomEmptyInputException extends RuntimeException {

    private String message;

    public CustomEmptyInputException(String message) {
        super(message);
        this.message = message;
    }

}

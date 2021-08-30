package com.flyhub.saccox.userservice.exception;

public class CustomJsonProcessingException extends RuntimeException {

    private String message;

    public CustomJsonProcessingException(String message) {
        super(message);
        this.message = message;
    }

}

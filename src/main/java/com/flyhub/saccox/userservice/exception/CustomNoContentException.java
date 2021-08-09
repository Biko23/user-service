package com.flyhub.saccox.userservice.exception;

public class CustomNoContentException extends RuntimeException {

    private String message;

    public CustomNoContentException(String message) {
        super(message);
        this.message = message;
    }

}

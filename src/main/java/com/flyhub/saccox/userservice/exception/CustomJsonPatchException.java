package com.flyhub.saccox.userservice.exception;

public class CustomJsonPatchException extends RuntimeException {

    private String message;

    public CustomJsonPatchException(String message) {
        super(message);
        this.message = message;
    }

}

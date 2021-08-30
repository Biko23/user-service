package com.flyhub.saccox.userservice.exception;

public class CustomNumberFormatException extends RuntimeException {

    private String message;

    public CustomNumberFormatException(String message) {
        super(message);
        this.message = message;
    }

}

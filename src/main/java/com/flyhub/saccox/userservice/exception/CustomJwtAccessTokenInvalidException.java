package com.flyhub.saccox.userservice.exception;

public class CustomJwtAccessTokenInvalidException extends RuntimeException {

    private String message;

    public CustomJwtAccessTokenInvalidException(String message) {
        super(message);
        this.message = message;
    }

}

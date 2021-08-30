package com.flyhub.saccox.userservice.exception;

public class CustomJwtAccessTokenExpiredException extends RuntimeException {

    private String message;

    public CustomJwtAccessTokenExpiredException(String message) {
        super(message);
        this.message = message;
    }

}

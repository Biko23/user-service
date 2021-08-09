package com.flyhub.saccox.userservice.exception;

public class CustomJwtRefreshTokenExpiredException extends RuntimeException {

    private String message;

    public CustomJwtRefreshTokenExpiredException(String message) {
        super(message);
        this.message = message;
    }

}

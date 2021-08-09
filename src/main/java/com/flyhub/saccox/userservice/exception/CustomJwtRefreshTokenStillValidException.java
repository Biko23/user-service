package com.flyhub.saccox.userservice.exception;

public class CustomJwtRefreshTokenStillValidException extends RuntimeException {

    private String message;

    public CustomJwtRefreshTokenStillValidException(String message) {
        super(message);
        this.message = message;
    }

}

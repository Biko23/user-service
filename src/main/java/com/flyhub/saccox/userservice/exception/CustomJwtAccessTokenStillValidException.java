package com.flyhub.saccox.userservice.exception;

public class CustomJwtAccessTokenStillValidException extends RuntimeException {

    private String message;

    public CustomJwtAccessTokenStillValidException(String message) {
        super(message);
        this.message = message;
    }

}

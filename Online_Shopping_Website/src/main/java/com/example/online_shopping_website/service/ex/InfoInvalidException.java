package com.example.online_shopping_website.service.ex;

public class RegisterInfoInvalidException extends RuntimeException{
    public RegisterInfoInvalidException() {
    }

    public RegisterInfoInvalidException(String message) {
        super(message);
    }

    public RegisterInfoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterInfoInvalidException(Throwable cause) {
        super(cause);
    }

    public RegisterInfoInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

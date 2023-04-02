package com.example.online_shopping_website.service.ex;

public class SQLRegisterInsertException extends RuntimeException{
    public SQLRegisterInsertException() {
    }

    public SQLRegisterInsertException(String message) {
        super(message);
    }

    public SQLRegisterInsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLRegisterInsertException(Throwable cause) {
        super(cause);
    }

    public SQLRegisterInsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

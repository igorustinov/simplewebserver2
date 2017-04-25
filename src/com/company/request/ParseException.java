package com.company.request;

/**
 * Created by igoru on 23-Apr-17.
 */
public class ParseException extends RuntimeException {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParseException() {
    }
}

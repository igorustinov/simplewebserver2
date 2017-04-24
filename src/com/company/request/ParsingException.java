package com.company.request;

/**
 * Created by igoru on 23-Apr-17.
 */
public class ParsingException extends RuntimeException {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParsingException() {
    }
}

package com.company.request;

/**
 * Created by igoru on 25-Apr-17.
 */
public class NotExistException extends RuntimeException {
    public NotExistException() {
        super();
    }

    public NotExistException(String message) {
        super(message);
    }

    public NotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistException(Throwable cause) {
        super(cause);
    }

    protected NotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

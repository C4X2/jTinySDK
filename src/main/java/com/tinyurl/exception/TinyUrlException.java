package com.tinyurl.exception;

/**
 * Base unchecked exception for all TinyURL SDK operations.
 */
public class TinyUrlException extends RuntimeException {

    public TinyUrlException(String message) {
        super(message);
    }

    public TinyUrlException(String message, Throwable cause) {
        super(message, cause);
    }
}

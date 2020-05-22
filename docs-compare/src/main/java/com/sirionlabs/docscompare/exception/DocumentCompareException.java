package com.sirionlabs.docscompare.exception;

/**
 * Wrapper for application's exceptions
 */
public class DocumentCompareException extends RuntimeException {

    public DocumentCompareException(String message) {
        super(message);
    }

    public DocumentCompareException(String message, Throwable cause) {
        super(message, cause);
    }
}

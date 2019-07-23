package com.devsda.hack.lmdtfy.exceptions;

public class SlotsValidationFailureException extends RuntimeException {

    public SlotsValidationFailureException(String message) {
        super(message);
    }

    public SlotsValidationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public SlotsValidationFailureException(Throwable cause) {
        super(cause);
    }

}

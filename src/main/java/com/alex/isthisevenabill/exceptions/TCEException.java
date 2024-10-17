package com.alex.isthisevenabill.exceptions;

public class TCEException extends RuntimeException {
    public TCEException(String message) {
        super(message);
    }

    public TCEException(String message, Throwable cause) {
        super(message, cause);
    }
}

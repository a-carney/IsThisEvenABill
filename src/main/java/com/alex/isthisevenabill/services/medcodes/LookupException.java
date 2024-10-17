package com.alex.isthisevenabill.services.medcodes;

public class LookupException extends Exception {

    public LookupException(String message) {
        super(message);
    }

    public LookupException(String message, Throwable cause) {
        super(message, cause);
    }
}

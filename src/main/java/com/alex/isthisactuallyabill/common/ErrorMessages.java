package com.alex.isthisactuallyabill.common;

import lombok.Getter;

// Enums for Error Messages
@Getter
public enum ErrorMessages {
    INVALID_INPUT("Invalid input data"),
    LOOKUP_FAILED("Failed to lookup code"),
    MISSING_FIELDS("Missing required fields: %s");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}

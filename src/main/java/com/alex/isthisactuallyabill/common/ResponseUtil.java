package com.alex.isthisactuallyabill.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    /**
     * Generates a standardized success response.
     *
     * @param data The data to include in the response body.
     * @return ResponseEntity with a 200 OK status and response body.
     */
    public static ResponseEntity<Object> successResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Generates a standardized error response for client errors (e.g., validation issues).
     *
     * @param errorMessage The error message to include in the response body.
     * @return ResponseEntity with a 400 Bad Request status and error details.
     */
    public static ResponseEntity<Object> errorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", errorMessage);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Generates a standardized server error response (e.g., exceptions, internal server errors).
     *
     * @param exception The exception to include in the response body.
     * @return ResponseEntity with a 500 Internal Server Error status and error details.
     */
    public static ResponseEntity<Object> serverErrorResponse(Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "error");
        response.put("message", "An internal server error occurred.");
        response.put("details", exception.getMessage()); // Optional: Include exception details
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

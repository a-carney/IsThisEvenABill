package com.alex.isthisactuallyabill.common;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

// Response Utility Class
class ResponseUtil {
    public static ResponseEntity<Object> successResponse(Object data) {
        return ResponseEntity.ok(data);
    }

    public static ResponseEntity<Object> errorResponse(String errorMessage) {
        Map<String, String> error = new HashMap<>();
        error.put("error", errorMessage);
        return ResponseEntity.badRequest().body(error);
    }

    public static ResponseEntity<Object> serverErrorResponse(Exception exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return ResponseEntity.status(500).body(error);
    }
}

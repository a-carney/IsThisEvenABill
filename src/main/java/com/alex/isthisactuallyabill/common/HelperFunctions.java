package com.alex.isthisactuallyabill.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Helper Functions
public class HelperFunctions {
    public static String validateRequiredFields(Map<String, Object> data, List<String> requiredFields) {
        List<String> missingFields = new ArrayList<>();
        for (String field : requiredFields) {
            if (!data.containsKey(field)) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            return String.format(ErrorMessages.MISSING_FIELDS.getMessage(), String.join(", ", missingFields));
        }
        return null;
    }

    public static Map<String, Object> makeApiRequest(String url, Map<String, String> params) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class, params);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}

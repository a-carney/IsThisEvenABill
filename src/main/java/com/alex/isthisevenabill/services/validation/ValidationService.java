package com.alex.isthisevenabill.services.validation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ValidationService {

    public String validateRequiredFields(Map<String, Object> data, List<String> requiredFields) {
        List<String> missingFields = new ArrayList<>();
        for (String field : requiredFields) {
            if (!data.containsKey(field)) {
                missingFields.add(field);
            }
        }
        if (!missingFields.isEmpty()) {
            return String.format("Missing required fields: %s", String.join(", ", missingFields));
        }
        return null;
    }
}
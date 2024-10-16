package com.alex.isthisactuallyabill.services;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class AbstractLookupService {

    protected RestTemplate restTemplate;
    protected String API_EXTERNAL;

    public AbstractLookupService(String apiUrl) {
        this.API_EXTERNAL = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    public abstract String lookupCode(String code);

    protected HttpHeaders getCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        // Add any common headers here, e.g., API key, Authorization token
        // headers.set("Authorization", "Bearer <token>");
        return headers;
    }

    protected String makeApiCall(String url) {
        try {
            HttpEntity<String> entity = new HttpEntity<>(getCommonHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return handleApiError(e);
        }
    }

    protected String handleApiError(Exception e) {
        return "Error occurred while making API call: " + e.getMessage();
    }
}

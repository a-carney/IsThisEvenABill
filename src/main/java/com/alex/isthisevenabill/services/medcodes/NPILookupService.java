package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.MediaType;

@Service
public class NPILookupService implements LookupService {

    private final String apiUrl;
    private final RestTemplate restTemplate;

    public NPILookupService(@Value("${api.npi-url}") String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public String lookup(String code) throws LookupException {
        check(code);
        try {
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("number", code)
                .toUriString();
            HttpEntity<String> entity = new HttpEntity<>(getHeaders());
            ResponseEntity<String> rsp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return process(rsp.getBody());
        } catch (RestClientException e) {
            throw new LookupException("Error fetching data from API with NPI: " + code, e);
        }
    }

    private String process(String rsp) {
        if (rsp == null || rsp.isEmpty()) {
            return "{\"error\": \"empty response from API\"}";
        }
        return rsp;
    }

    @Override
    public void check(String code) throws LookupException {
        if (code == null || code.isEmpty()) {
            throw new LookupException("Invalid input - NPI code cannot be null or empty");
        }
        if (!code.matches("\\d{10}")) {
            throw new LookupException("Invalid NPI format - must be a 10-digit number");
        }
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders hdrs = new HttpHeaders();
        hdrs.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return hdrs;
    }

    @Override
    public CodeType getCodeType() {
        return CodeType.NPI;
    }
}

package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ICDLookupService implements LookupService {

    @Value("${api.icd-url}")
    private String icdUrl;

    private final RestTemplate restTemplate;

    public ICDLookupService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(value = "icdCodes", key = "#code")
    public String lookup(String code) throws LookupException {
        check(code);
        try {
            String url = UriComponentsBuilder.fromHttpUrl(icdUrl)
                    .queryParam("sf", "code")
                    .queryParam("terms", code)
                    .toUriString();

            HttpEntity<String> entity = new HttpEntity<>(getHeaders());
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return process(response.getBody());
        } catch (RestClientException e) {
            throw new LookupException("Error fetching data from API with ICD code: " + code, e);
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
            throw new LookupException("Invalid input - ICD code cannot be null or empty");
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
        return CodeType.ICD;
    }
}

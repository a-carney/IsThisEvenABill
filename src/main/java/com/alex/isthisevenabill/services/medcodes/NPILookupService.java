package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NPILookupService extends AbstractLookupService implements LookupService {

    public NPILookupService(@Value("${api.npi-url}") String apiUrl) {
        super(apiUrl);
    }

    @Override
    protected HttpHeaders getHeaders() {
        HttpHeaders hdrs = new HttpHeaders();
        hdrs.set(CONTENT_TYPE, APPLICATION_JSON);
        return hdrs;
    }

    @Override
    protected String process(String rsp) {
        if (rsp == null || rsp.isEmpty()) {
            return "{\"error\": \"empty response from API\"}";
        }
        return rsp;
    }

    @Override
    protected void check(String code) throws LookupException {
        if (code == null || code.isEmpty()) {
            throw new LookupException("Invalid input - NPI code cannot be null or empty");
        }
        if (!code.matches("\\d{10}")) {
            throw new LookupException("Invalid NPI format - must be a 10-digit number");
        }
    }

    @Override
    public String send(String code, HttpHeaders headers) throws LookupException {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("number", code)
                .toUriString();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> rsp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return rsp.getBody();
        } catch (RestClientException e) {
            throw new LookupException("Error fetching data from API with NPI: " + code, e);
        }
    }
}

package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class ICDLookupService extends AbstractLookupService implements LookupService {

    @Value("${api.icd-url}")
    private String icdUrl;

/*
    `           WHAT IS AN ICD-10 CODE?
        ICD, or International Classification of Diseases, are used in medical billing to described a diagnosis with a CPT code
 */

    public ICDLookupService(@Value("${api.icd-url}") String apiUrl) {
        super(apiUrl);
    }

    @Override
    @Cacheable(value = "icdCodes", key = "#code")
    public String send(String code, HttpHeaders headers) throws LookupException {
        check(code);
        try {
            String url = UriComponentsBuilder.fromHttpUrl(icdUrl)
                    .queryParam("sf", "code")
                    .queryParam("terms", code)
                    .toUriString();

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return process(response.getBody());
        } catch (RestClientException e) {
            throw new LookupException("Error fetching data from API with ICD code: " + code, e);
        }
    }

    @Override
    protected String process(String rsp) {
        if (rsp == null || rsp.isEmpty()) {
            return "{\"error\": \"empty response from API\"}";
        }
        // Here you can parse the response as needed
        return rsp; // Placeholder return
    }

    @Override
    protected void check(String code) throws LookupException {
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
    public String getDescription() {
        return "ICD-10 Code Lookup Service";
    }

    @Override
    public String getApiEndpoint() {
        return icdUrl;
    }
}
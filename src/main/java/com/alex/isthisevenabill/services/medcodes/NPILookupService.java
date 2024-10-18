package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@Service
public class NPILookupService extends AbstractLookupService implements LookupService {

    @Value("${api.npi-url}")
    private String apiUrl;


/*
    `           WHAT IS AN NPI CODE?
        NPI, or National Provider Indentifier, is used to represent a person place or thing in a medical claim (i.e. doctor or office)

 */

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
            return "{'error': 'empty response from API'}";
        }
        return rsp;
    }

    @Override
    protected void check(String code) throws LookupException {
        if (code == null || code.isEmpty()) {
            throw new LookupException("invalid input - code cannot be null or empty");
        }
    }

    @Override
    public String send(String code, HttpHeaders headers) throws LookupException {
        try {
            String url = apiUrl + "&number=" + code;
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> rsp = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            return rsp.getBody();
        } catch (RestClientException e) {
            throw new LookupException("Error fetching data from API with NPI: " + code, e);
        }
    }

    @Override
    public String execute(String code) throws LookupException {
        return super.execute(code);
    }
}
package com.alex.isthisevenabill.services.medcodes;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractLookupService implements LookupService {

    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String APPLICATION_JSON = "application/json";

    protected final String apiUrl;
    protected final RestTemplate restTemplate;

    public AbstractLookupService(String apiUrl) {
        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public String execute(String code) throws LookupException {
        check(code);
        HttpHeaders headers = getHeaders();
        String response = send(code, headers);
        return process(response);
    }

    public abstract HttpHeaders getHeaders();

    public abstract String process(String response);

    public abstract void check(String code) throws LookupException;

    public abstract String send(String code, HttpHeaders headers) throws LookupException;
}

package com.alex.isthisevenabill.services.medcodes;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public abstract class AbstractLookupService {

    protected RestTemplate restTemplate;
    protected String API_EXTERNAL;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";


    protected AbstractLookupService(String apiUrl) {
        this.API_EXTERNAL = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    protected String execute(String code) throws LookupException {
        check(code);
        HttpHeaders headers = getHeaders();
        String rsp = send(code, headers);
        return process(rsp);

    }

    protected abstract HttpHeaders getHeaders();

    protected abstract String process(String rsp);

    protected abstract void check(String code) throws LookupException;

    public abstract String send(String code, HttpHeaders headers) throws LookupException;

}
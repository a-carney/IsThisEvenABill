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

    public final String lookup(String code) throws LookupException {
        check(code);
        HttpHeaders headers = new HttpHeaders();
        String rsp = fetchFromAPI(code, headers);
        return parse(rsp);

    }

    protected abstract String parse(String rsp);

    protected abstract void check(String code);

    public abstract String fetchFromAPI(String code, HttpHeaders headers) throws LookupException;

}
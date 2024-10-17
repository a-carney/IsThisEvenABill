package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

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

}
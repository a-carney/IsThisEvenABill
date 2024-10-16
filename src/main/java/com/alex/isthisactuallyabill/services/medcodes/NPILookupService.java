package com.alex.isthisactuallyabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NPILookupService extends AbstractLookupService implements LookupService {

    @Value("${api.npi-url}")
    private String apiUrl;

    public NPILookupService(@Value("${api.npi-url}") String apiUrl) {
        super(apiUrl);
    }

    @Override
    @Cacheable(value = "npiCodes", key = "#code")
    public String lookupCode(String code) throws LookupException {
        String response = makeApiCall(API_EXTERNAL + "?number=" + code);
        if (response == null || response.isEmpty()) {
            throw new LookupException("NPI code lookup failed for code: " + code);
        }
        return response;
    }
}
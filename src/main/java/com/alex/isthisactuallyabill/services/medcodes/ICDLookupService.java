package com.alex.isthisactuallyabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ICDLookupService extends AbstractLookupService implements LookupService {

    @Value("${api.icd-url}")
    private String icdUrl;

    public ICDLookupService(@Value("${api.icd-url}") String apiUrl) {
        super(apiUrl);
    }

    @Override
    @Cacheable(value = "icdCodes", key = "#code")
    public String lookupCode(String code) throws LookupException {
        String response = makeApiCall(API_EXTERNAL + "?terms=" + code);
        if (response == null || response.isEmpty()) {
            throw new LookupException("ICD-10 code lookup failed for code: " + code);
        }
        return response;
    }
}
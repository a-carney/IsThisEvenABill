package com.alex.isthisactuallyabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CPTLookupService extends AbstractLookupService implements LookupService {

    @Value("${api.cpt-url}")
    private String urlApi;

    @Override
    @Cacheable(value = "cptCodes", key = "#code")
    public String lookupCode(String code) throws LookupException {
        String response = makeApiCall(urlApi + "?code=" + code);
        if (response == null || response.isEmpty()) {
            throw new LookupException("CPT code lookup failed for code: " + code);
        }
        return response;
    }
}
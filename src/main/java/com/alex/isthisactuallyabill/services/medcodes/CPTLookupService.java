package com.alex.isthisactuallyabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CPTLookupService extends AbstractLookupService implements LookupService {

    @Value("${api.cpt-url}")
    private String urlApi;

    protected CPTLookupService(String apiUrl) {
        super(apiUrl);
    }

    @Override
    @Cacheable(value = "cptCodes", key = "#code")
    public String lookupCode(String code) throws LookupException {
        return performLookup(urlApi + "?code=" + code);
    }
}
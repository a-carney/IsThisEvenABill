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
    public String fetchFromAPI(String code, HttpHeaders headers) throws LookupException {
        return performLookup(API_EXTERNAL + "?terms=" + code);
    }
}
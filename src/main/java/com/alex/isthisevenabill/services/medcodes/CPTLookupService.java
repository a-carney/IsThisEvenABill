package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class CPTLookupService extends AbstractLookupService implements LookupService {

    @Value("${api.cpt-url}")
    private String urlApi;

/*
    `           WHAT IS A CPT CODE?

        CPT, or Current Procedural Terminology (sp?), is the coding system used for medical procedures when
        creating a medical bill. These codes often contain ICD-10 codes with them, which is discussed in ICDLookupService
 */

    protected CPTLookupService(String apiUrl) {
        super(apiUrl);
    }

    @Override
    @Cacheable(value = "cptCodes", key = "#code")
    public String fetchFromAPI(String code, HttpHeaders headers) throws LookupException {
        return performLookup(urlApi + "?code=" + code);
    }
}
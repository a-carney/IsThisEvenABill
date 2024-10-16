package com.alex.isthisactuallyabill.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CodeLookupService {

    private final RestTemplate restTemplate;

    @Value("${api.cpt-url}")
    private String cptApiUrl;

    @Value("${api.icd-url}")
    private String icdApiUrl;

    @Value("${api.npi-url}")
    private String npiApiUrl;

    @Autowired
    public CodeLookupService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String lookupCPTCode(String cptCode) {
        String url = cptApiUrl + "?code=" + cptCode;
        return restTemplate.getForObject(url, String.class);
    }

    public String lookupICDCode(String icdCode) {
        String url = icdApiUrl + "?code=" + icdCode;
        return restTemplate.getForObject(url, String.class);
    }

    public String lookupNPI(String npi) {
        String url = npiApiUrl + "?number=" + npi;
        return restTemplate.getForObject(url, String.class);
    }

    public Map<String, Object> lookupCode(String codeType, String code) {
        return null;
    }
}
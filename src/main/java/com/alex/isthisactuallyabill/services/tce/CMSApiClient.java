package com.alex.isthisactuallyabill.services.tce;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CMSApiClient {

    private static final String CMS_API_BASE_URL = "https://data.cms.gov/provider-data/api";

    private final RestTemplate restTemplate;

    public CMSApiClient() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Fetch Medicare cost for a given CPT code and ZIP code from CMS API
     *
     * @param cptCode - CPT procedure code
     * @param zipCode - ZIP code for the region
     * @return Medicare cost for the procedure
     */
    public double getMedicareCost(String cptCode, String zipCode) {
        String url = UriComponentsBuilder.fromHttpUrl(CMS_API_BASE_URL)
                .path("/physician-fee-schedule")
                .queryParam("cptCode", cptCode)
                .queryParam("zipCode", zipCode)
                .toUriString();

        // Replace this with actual response mapping logic
        CMSResponse response = restTemplate.getForObject(url, CMSResponse.class);

        // Assume response contains a "medicarePayment" field with the cost
        if (response != null && response.getMedicarePayment() != null) {
            return response.getMedicarePayment();
        } else {
            throw new RuntimeException("Failed to fetch Medicare payment data from CMS API");
        }
    }
}

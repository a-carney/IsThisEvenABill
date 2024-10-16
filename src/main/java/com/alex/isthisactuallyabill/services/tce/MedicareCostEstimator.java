package com.alex.isthisactuallyabill.services.tce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicareCostEstimator {

    private final CMSApiClient cmsApiClient;

    @Autowired
    public MedicareCostEstimator(CMSApiClient cmsApiClient) {
        this.cmsApiClient = cmsApiClient;
    }

    /**
     * Estimate Medicare out-of-pocket (OOP) costs for a procedure in a specific region
     *
     * @param cptCode - CPT procedure code
     * @param zipCode - ZIP code for the region
     * @return Estimated Medicare OOP cost
     * @throws TCEException in case of error fetching data
     */
    public String estimateCost(String cptCode, String zipCode) throws TCEException {
        try {
            // Call CMS API Client to fetch cost data
            double medicarePayment = cmsApiClient.getMedicareCost(cptCode, zipCode);

            // Medicare covers 80%, calculate OOP (20%)
            double patientResponsibility = medicarePayment * 0.20;
            return "Medicare Payment: $" + medicarePayment + ", Patient OOP: $" + patientResponsibility;
        } catch (Exception e) {
            throw new TCEException("Failed to estimate Medicare cost", e);
        }
    }
}
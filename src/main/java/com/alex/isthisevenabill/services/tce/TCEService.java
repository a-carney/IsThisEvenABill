package com.alex.isthisevenabill.services.tce;

import com.alex.isthisevenabill.exceptions.TCEException;
import com.alex.isthisevenabill.models.HealthPlanDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TCEService {

    private final MedicareCostEstimator medicareCostEstimator;

    public TCEService(MedicareCostEstimator medicareCostEstimator) {
        this.medicareCostEstimator = medicareCostEstimator;
    }

    @Cacheable(value = "costEstimates", key = "#cptCode + #zipCode + #npi + #healthPlanDetails.toString()")
    public String calculateTotalCostEstimate(String cptCode, String zipCode, String npi, HealthPlanDetails healthPlanDetails) throws TCEException {
        try {
            String medicareEstimate = medicareCostEstimator.estimateCost(cptCode, zipCode);
            // Apply health plan details to the estimate
            // This is a placeholder for the actual calculation
            return "Total Cost Estimate (Medicare): " + medicareEstimate;
        } catch (Exception e) {
            throw new TCEException("Failed to calculate total cost estimate", e);
        }
    }
}

package com.alex.isthisactuallyabill.services.tce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TCEService {

    private final MedicareCostEstimator medicareCostEstimator;

    @Autowired
    public TCEService(MedicareCostEstimator medicareCostEstimator) {
        this.medicareCostEstimator = medicareCostEstimator;
    }

    /**
     * Calculate the total cost estimate for a given CPT code, ZIP code, and NPI
     *
     * @param cptCode - CPT procedure code
     * @param zipCode - Patient's ZIP code
     * @param npi     - National Provider Identifier
     * @return Total cost estimate including Medicare OOP costs
     * @throws TCEException if cost estimation fails
     */
    public String calculateTotalCostEstimate(String cptCode, String zipCode, String npi) throws TCEException {
        try {
            String medicareEstimate = medicareCostEstimator.estimateCost(cptCode, zipCode);
            return "Total Cost Estimate (Medicare): " + medicareEstimate;
        } catch (Exception e) {
            throw new TCEException("Failed to calculate total cost estimate", e);
        }
    }
}

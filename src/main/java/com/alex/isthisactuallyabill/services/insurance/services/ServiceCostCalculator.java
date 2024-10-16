package com.alex.isthisactuallyabill.services.insurance.services;

import com.alex.isthisactuallyabill.services.insurance.models.ServiceCost;

public class ServiceCostCalculator {

    public static Double calculateOutOfPocket(ServiceCost serviceCost, Double serviceCharge, Double deductible, Double currentSpent, Double outOfPocketMax) {
        double cost = 0.0;

        // Check if the service applies to the deductible
        if (serviceCost.getAppliesToDeductible()) {
            // Deductible hasn't been met
            if (currentSpent < deductible) {
                Double remainingDeductible = deductible - currentSpent;
                if (serviceCharge <= remainingDeductible) {
                    cost = serviceCharge;
                    currentSpent += serviceCharge;
                    return Math.min(cost, outOfPocketMax); // Cap by OOP max
                } else {
                    // Partial deductible, then apply coinsurance/copay for the rest
                    cost = remainingDeductible;
                    currentSpent = deductible;
                    serviceCharge -= remainingDeductible;
                }
            }
        }

        // Apply copay or coinsurance after deductible
        if (serviceCost.getCopay() != null) {
            cost += serviceCost.getCopay();
        } else if (serviceCost.getCoinsurance() != null) {
            cost += serviceCharge * serviceCost.getCoinsurance();
        }

        // Ensure the cost doesn't exceed out-of-pocket max
        return Math.min(cost, outOfPocketMax);
    }
}

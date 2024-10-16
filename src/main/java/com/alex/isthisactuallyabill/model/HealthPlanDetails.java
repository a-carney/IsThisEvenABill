package com.alex.isthisactuallyabill.model;

public class HealthPlanDetails {
    private Double deductible;
    private Double copay;
    private Double coinsurance;
    private Double outOfPocketMax;

    public HealthPlanDetails(Double deductible, Double copay, Double coinsurance, Double outOfPocketMax) {
        this.deductible = deductible;
        this.copay = copay;
        this.coinsurance = coinsurance;
        this.outOfPocketMax = outOfPocketMax;
    }

    // Getters and Setters
}

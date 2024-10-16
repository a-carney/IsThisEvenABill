package com.alex.isthisactuallyabill.models;

public class HealthPlanDetails {

    private final Double deductible;
    private final Double copay;
    private final Double coinsurance;
    private final Double outOfPocketMax;

    // Constructor
    public HealthPlanDetails(Double deductible, Double copay, Double coinsurance, Double outOfPocketMax) {
        this.deductible = deductible;
        this.copay = copay;
        this.coinsurance = coinsurance;
        this.outOfPocketMax = outOfPocketMax;
    }

    // Getters

    public Double getDeductible() {
        return deductible;
    }

    public Double getCopay() {
        return copay;
    }

    public Double getCoinsurance() {
        return coinsurance;
    }

    public Double getOutOfPocketMax() {
        return outOfPocketMax;
    }
}
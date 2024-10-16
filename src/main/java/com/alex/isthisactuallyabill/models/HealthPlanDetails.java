package com.alex.isthisactuallyabill.models;

// Model Classes
class HealthPlanDetails {
    private Double deductible;
    private Double copay;
    private Double coinsurance;
    private Double outOfPocketMax;

    // Constructors
    public HealthPlanDetails() {
    }

    public HealthPlanDetails(Double deductible, Double copay, Double coinsurance, Double outOfPocketMax) {
        this.deductible = deductible;
        this.copay = copay;
        this.coinsurance = coinsurance;
        this.outOfPocketMax = outOfPocketMax;
    }

    // Getters and Setters
    public Double getDeductible() {
        return deductible;
    }

    public void setDeductible(Double deductible) {
        this.deductible = deductible;
    }

    public Double getCopay() {
        return copay;
    }

    public void setCopay(Double copay) {
        this.copay = copay;
    }

    public Double getCoinsurance() {
        return coinsurance;
    }

    public void setCoinsurance(Double coinsurance) {
        this.coinsurance = coinsurance;
    }

    public Double getOutOfPocketMax() {
        return outOfPocketMax;
    }

    public void setOutOfPocketMax(Double outOfPocketMax) {
        this.outOfPocketMax = outOfPocketMax;
    }
}

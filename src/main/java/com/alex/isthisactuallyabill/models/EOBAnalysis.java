package com.alex.isthisactuallyabill.models;

// EOBAnalysis extends MedicalRequest
class EOBAnalysis extends MedicalRequest {
    private Double billedAmount;

    // Constructors
    public EOBAnalysis() {
    }

    public EOBAnalysis(String cptCode, String icd10Code, String npiCode, HealthPlanDetails healthPlanDetails, Double billedAmount) {
        super(cptCode, icd10Code, npiCode, healthPlanDetails);
        this.billedAmount = billedAmount;
    }

    // Getters and Setters
    public Double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Double billedAmount) {
        this.billedAmount = billedAmount;
    }
}

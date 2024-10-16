package com.alex.isthisactuallyabill.model;

public class TreatmentCostEstimate {
    private String cptCode;
    private String icd10Code;
    private String npiCode;
    private String zipCode;
    private HealthPlanDetails healthPlanDetails;

    public TreatmentCostEstimate(String cptCode, String icd10Code, String npiCode, String zipCode, HealthPlanDetails healthPlanDetails) {
        this.cptCode = cptCode;
        this.icd10Code = icd10Code;
        this.npiCode = npiCode;
        this.zipCode = zipCode;
        this.healthPlanDetails = healthPlanDetails;
    }

    // Getters and Setters
}

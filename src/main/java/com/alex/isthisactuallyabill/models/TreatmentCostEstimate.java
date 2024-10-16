package com.alex.isthisactuallyabill.models;

public class TreatmentCostEstimate {

    private String cptCode;
    private String icd10Code;
    private String npiCode;
    private String zipCode;
    private HealthPlanDetails healthPlanDetails;

    // Constructor
    public TreatmentCostEstimate(String cptCode, String icd10Code, String npiCode, String zipCode, HealthPlanDetails healthPlanDetails) {
        this.cptCode = cptCode;
        this.icd10Code = icd10Code;
        this.npiCode = npiCode;
        this.zipCode = zipCode;
        this.healthPlanDetails = healthPlanDetails;
    }

    // Getters and Setters
    public String getCptCode() {
        return cptCode;
    }

    public void setCptCode(String cptCode) {
        this.cptCode = cptCode;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getNpiCode() {
        return npiCode;
    }

    public void setNpiCode(String npiCode) {
        this.npiCode = npiCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public HealthPlanDetails getHealthPlanDetails() {
        return healthPlanDetails;
    }

    public void setHealthPlanDetails(HealthPlanDetails healthPlanDetails) {
        this.healthPlanDetails = healthPlanDetails;
    }
}

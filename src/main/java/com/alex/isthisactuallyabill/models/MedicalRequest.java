package com.alex.isthisactuallyabill.models;

// Base class for medical requests
abstract class MedicalRequest {
    protected String cptCode;
    protected String icd10Code;
    protected String npiCode;
    protected HealthPlanDetails healthPlanDetails;

    // Constructors
    public MedicalRequest() {
    }

    public MedicalRequest(String cptCode, String icd10Code, String npiCode, HealthPlanDetails healthPlanDetails) {
        this.cptCode = cptCode;
        this.icd10Code = icd10Code;
        this.npiCode = npiCode;
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

    public HealthPlanDetails getHealthPlanDetails() {
        return healthPlanDetails;
    }

    public void setHealthPlanDetails(HealthPlanDetails healthPlanDetails) {
        this.healthPlanDetails = healthPlanDetails;
    }
}

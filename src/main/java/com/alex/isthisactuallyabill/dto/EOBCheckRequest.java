package com.alex.isthisactuallyabill.dto;

import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EOBCheckRequest {

    @NotBlank(message = "CPT code is required.")
    private String cptCode;

    @NotBlank(message = "ICD-10 code is required.")
    private String icd10Code;

    @NotBlank(message = "NPI code is required.")
    private String npiCode;

    @Valid
    @NotNull(message = "Health plan details are required.")
    private HealthPlanDetails healthPlanDetails;

    @NotNull(message = "Billed amount is required.")
    private Double billedAmount;

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

    public Double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Double billedAmount) {
        this.billedAmount = billedAmount;
    }
}
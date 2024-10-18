package com.alex.isthisevenabill.dto;

import com.alex.isthisevenabill.models.HealthPlanDetails;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TreatmentCostEstimateRequest {

    @NotBlank(message = "CPT code is required")
    @Pattern(regexp = "\\d{5}", message = "CPT code must be a 5-digit number")
    private String cptCode;

    @NotBlank(message = "ICD-10 code is required")
    private String icd10Code;

    @NotBlank(message = "NPI code is required")
    private String npiCode;

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "\\d{5}", message = "ZIP code must be a 5-digit number")
    private String zipCode;

    @NotNull(message = "Health plan details are required")
    private HealthPlanDetails healthPlanDetails;

    // Getters and setters
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

package com.alex.isthisevenabill.model;

import lombok.Getter;

@Getter
public class EOBAnalysis extends MedicalRequest {
    private final Double billedAmount;

    public EOBAnalysis(String cptCode, String icd10Code, String npiCode, HealthPlanDetails healthPlanDetails, Double billedAmount) {
        super(cptCode, icd10Code, npiCode, healthPlanDetails);
        this.billedAmount = billedAmount;
    }
}

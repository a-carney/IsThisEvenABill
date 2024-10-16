package com.alex.isthisactuallyabill.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EOBAnalysis extends MedicalRequest {
    private Double billedAmount;

    public EOBAnalysis(String cptCode, String icd10Code, String npiCode, HealthPlanDetails healthPlanDetails, Double billedAmount) {
        super(cptCode, icd10Code, npiCode, healthPlanDetails);
        this.billedAmount = billedAmount;
    }

}

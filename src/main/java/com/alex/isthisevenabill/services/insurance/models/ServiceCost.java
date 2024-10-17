package com.alex.isthisevenabill.services.insurance.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceCost {

    // Getters and Setters
    private Double copay;
    private Double coinsurance;
    private Boolean appliesToDeductible;

    public ServiceCost(Double copay, Double coinsurance, Boolean appliesToDeductible) {
        this.copay = copay;
        this.coinsurance = coinsurance;
        this.appliesToDeductible = appliesToDeductible;
    }

    @Override
    public String toString() {
        return "ServiceCost{" +
                "copay=" + copay +
                ", coinsurance=" + coinsurance +
                ", appliesToDeductible=" + appliesToDeductible +
                '}';
    }
}


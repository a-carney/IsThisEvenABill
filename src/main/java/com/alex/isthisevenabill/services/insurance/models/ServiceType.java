package com.alex.isthisevenabill.services.insurance.models;

import lombok.Getter;

@Getter
public enum ServiceType {
    PCP("Medical", "Primary Care Physician"),
    SPECIALIST("Medical", "Specialist"),
    ER("Medical", "Emergency Room"),
    BEHAVIORAL_HEALTH("Behavioral", "Mental Health Services"),
    CHIROPRACTIC("Medical", "Chiropractic Care"),
    DRUG_TIER_1("Drug", "Tier 1 Drug"),
    DRUG_TIER_2("Drug", "Tier 2 Drug"),
    DRUG_TIER_3("Drug", "Tier 3 Drug");

    private final String category;
    private final String description;

    ServiceType(String category, String description) {
        this.category = category;
        this.description = description;
    }

}


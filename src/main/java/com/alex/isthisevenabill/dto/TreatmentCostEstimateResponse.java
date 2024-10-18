package com.alex.isthisevenabill.dto;

public class TreatmentCostEstimateResponse {
    private String estimatedCost;

    public TreatmentCostEstimateResponse(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(String estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
}

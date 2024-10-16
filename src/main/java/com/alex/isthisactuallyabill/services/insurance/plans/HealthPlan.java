package com.alex.isthisactuallyabill.services.insurance.plans;

import com.alex.isthisactuallyabill.services.insurance.models.ServiceCost;
import com.alex.isthisactuallyabill.services.insurance.models.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

public abstract class HealthPlan {

    @Getter
    protected Double outOfPocketMax;

    @Getter
    protected Double deductible;

    @Getter
    @Setter
    protected Double currentSpent;  // Tracks how much the user has already spent towards their deductible

    protected Map<ServiceType, ServiceCost> serviceCostMap;

    public HealthPlan(Double outOfPocketMax, Double deductible) {
        this.outOfPocketMax = outOfPocketMax;
        this.deductible = deductible;
        this.currentSpent = 0.0;
        this.serviceCostMap = new EnumMap<>(ServiceType.class);
    }

    // Add service costs for specific service types (e.g., PCP, ER, etc.)
    public void addServiceCost(ServiceType serviceType, ServiceCost serviceCost) {
        serviceCostMap.put(serviceType, serviceCost);
    }

    // Abstract method for calculating out-of-pocket cost, which can be overridden
    public abstract Double calculateOutOfPocketCost(ServiceType serviceType, Double serviceCharge);

}

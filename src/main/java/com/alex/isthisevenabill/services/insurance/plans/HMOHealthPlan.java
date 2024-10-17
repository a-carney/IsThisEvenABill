package com.alex.isthisevenabill.services.insurance.plans;

import com.alex.isthisevenabill.services.insurance.models.ServiceCost;
import com.alex.isthisevenabill.services.insurance.models.ServiceType;
import com.alex.isthisevenabill.services.insurance.services.ServiceCostCalculator;

public class HMOHealthPlan extends HealthPlan {

    public HMOHealthPlan(Double outOfPocketMax, Double deductible) {
        super(outOfPocketMax, deductible);
    }

    @Override
    public Double calculateOutOfPocketCost(ServiceType serviceType, Double serviceCharge) {
        ServiceCost serviceCost = serviceCostMap.get(serviceType);
        if (serviceCost != null) {
            return ServiceCostCalculator.calculateOutOfPocket(serviceCost, serviceCharge, this.deductible, this.currentSpent, this.outOfPocketMax);
        } else {
            throw new IllegalArgumentException("Service type not covered under this plan.");
        }
    }
}

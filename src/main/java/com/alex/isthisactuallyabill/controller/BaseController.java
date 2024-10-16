package com.alex.isthisactuallyabill.controller;

import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import com.alex.isthisactuallyabill.services.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public abstract class BaseController {

    private final ValidationService validationService;

    @Autowired
    public BaseController(ValidationService validationService) {
        this.validationService = validationService;
    }

    protected BaseController() {
    }

    protected String validateFields(Map<String, Object> data, Iterable<String> requiredFields) {
        return validationService.validateRequiredFields(data, (List<String>) requiredFields);
    }

    /**
     * Helper method to map healthPlanDetails from the request body.
     *
     * @param data The health plan details from the request body.
     * @return A HealthPlanDetails object.
     */
    protected HealthPlanDetails mapToHealthPlanDetails(Map<String, Object> data) {
        if (data == null) {
            return new HealthPlanDetails(null, null, null, null); // Return empty object
        }
        Double deductible = data.get("deductible") != null ? Double.valueOf(data.get("deductible").toString()) : null;
        Double copay = data.get("copay") != null ? Double.valueOf(data.get("copay").toString()) : null;
        Double coinsurance = data.get("coinsurance") != null ? Double.valueOf(data.get("coinsurance").toString()) : null;
        Double outOfPocketMax = data.get("out_of_pocket_max") != null ? Double.valueOf(data.get("out_of_pocket_max").toString()) : null;

        return new HealthPlanDetails(deductible, copay, coinsurance, outOfPocketMax);
    }
}
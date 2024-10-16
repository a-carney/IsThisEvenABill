package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import com.alex.isthisactuallyabill.models.TreatmentCostEstimate;
import com.alex.isthisactuallyabill.services.tce.TCEService;
import com.alex.isthisactuallyabill.services.tce.TCEException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TreatmentCostEstimatorController extends BaseController {

    private final TCEService tceService;
    private static final List<String> REQUIRED_FIELDS = List.of("cptCode", "icd10Code", "npiCode", "zipCode", "healthPlanDetails");

    public TreatmentCostEstimatorController(TCEService tceService) {
        this.tceService = tceService;
    }

    @PostMapping("/tce/estimate")
    public ResponseEntity<Object> estimate(@RequestBody Map<String, Object> data) {
        try {
            // Validate required fields
            String error = validateFields(data, REQUIRED_FIELDS);
            if (error != null) {
                return createErrorResponse(error);
            }

            // Map data to TreatmentCostEstimate model
            TreatmentCostEstimate estimate = mapToTreatmentCostEstimate(data);

            // Call TCEService to get the cost estimate
            String estimatedCost;
            try {
                estimatedCost = tceService.calculateTotalCostEstimate(estimate.getCptCode(), estimate.getZipCode(), estimate.getNpiCode());
            } catch (TCEException e) {
                return createErrorResponse("Failed to calculate the cost estimate: " + e.getMessage());
            }

            // Prepare and return the response
            Map<String, Object> response = new HashMap<>();
            response.put("estimatedCost", estimatedCost);
            return createSuccessResponse(response);

        } catch (Exception e) {
            return createServerErrorResponse(e);
        }
    }

    // Map request data to TreatmentCostEstimate
    private TreatmentCostEstimate mapToTreatmentCostEstimate(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        String zipCode = (String) data.get("zipCode");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails((Map<String, Object>) data.get("healthPlanDetails"));

        return new TreatmentCostEstimate(cptCode, icd10Code, npiCode, zipCode, healthPlanDetails);
    }
}

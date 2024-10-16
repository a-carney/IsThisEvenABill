package com.alex.isthisactuallyabill.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Controllers
@RestController
class TreatmentCostEstimatorController {

    private static final List<String> REQUIRED_FIELDS = Arrays.asList("cptCode", "icd10Code", "npiCode", "healthPlanDetails");

    @PostMapping("/tce/estimate")
    public ResponseEntity<Object> estimate(@RequestBody Map<String, Object> data) {
        try {
            String error = HelperFunctions.validateRequiredFields(data, REQUIRED_FIELDS);
            if (error != null) {
                return ResponseUtil.errorResponse(error);
            }

            // Map data to TreatmentCostEstimate model
            TreatmentCostEstimate estimate = mapToTreatmentCostEstimate(data);

            // Process estimation logic (placeholder)
            Double estimatedCost = 100.0;
            Map<String, Object> costBreakdown = new HashMap<>();
            costBreakdown.put("details", "Cost breakdown details here");

            Map<String, Object> response = new HashMap<>();
            response.put("estimatedCost", estimatedCost);
            response.put("costBreakdown", costBreakdown);

            return ResponseUtil.successResponse(response);
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e);
        }
    }

    private TreatmentCostEstimate mapToTreatmentCostEstimate(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        Map<String, Object> healthPlanDetailsMap = (Map<String, Object>) data.get("healthPlanDetails");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails(healthPlanDetailsMap);

        return new TreatmentCostEstimate(cptCode, icd10Code, npiCode, healthPlanDetails);
    }

    private HealthPlanDetails mapToHealthPlanDetails(Map<String, Object> data) {
        if (data == null) {
            return null;
        }
        Double deductible = data.get("deductible") != null ? Double.valueOf(data.get("deductible").toString()) : null;
        Double copay = data.get("copay") != null ? Double.valueOf(data.get("copay").toString()) : null;
        Double coinsurance = data.get("coinsurance") != null ? Double.valueOf(data.get("coinsurance").toString()) : null;
        Double outOfPocketMax = data.get("out_of_pocket_max") != null ? Double.valueOf(data.get("out_of_pocket_max").toString()) : null;
        return new HealthPlanDetails(deductible, copay, coinsurance, outOfPocketMax);
    }
}

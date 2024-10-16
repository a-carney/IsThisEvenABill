package com.alex.isthisactuallyabill.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
class EOBCheckerController {

    private static final List<String> REQUIRED_FIELDS = Arrays.asList("cptCode", "icd10Code", "npiCode", "healthPlanDetails", "billedAmount");

    @PostMapping("/eob/check")
    public ResponseEntity<Object> check(@RequestBody Map<String, Object> data) {
        try {
            String error = HelperFunctions.validateRequiredFields(data, REQUIRED_FIELDS);
            if (error != null) {
                return ResponseUtil.errorResponse(error);
            }

            // Map data to EOBAnalysis model
            EOBAnalysis analysis = mapToEOBAnalysis(data);

            // Process analysis logic (placeholder)
            String analysisResult = "All details match. No discrepancies.";
            List<String> discrepancies = new ArrayList<>();

            Map<String, Object> response = new HashMap<>();
            response.put("analysisResult", analysisResult);
            response.put("discrepancies", discrepancies);

            return ResponseUtil.successResponse(response);
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e);
        }
    }

    private EOBAnalysis mapToEOBAnalysis(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        Map<String, Object> healthPlanDetailsMap = (Map<String, Object>) data.get("healthPlanDetails");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails(healthPlanDetailsMap);
        Double billedAmount = data.get("billedAmount") != null ? Double.valueOf(data.get("billedAmount").toString()) : null;

        return new EOBAnalysis(cptCode, icd10Code, npiCode, healthPlanDetails, billedAmount);
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

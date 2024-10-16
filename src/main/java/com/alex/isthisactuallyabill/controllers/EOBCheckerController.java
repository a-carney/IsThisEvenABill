package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.models.EOBAnalysis;
import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EOBCheckerController extends BaseController {

    private static final List<String> REQUIRED_FIELDS = List.of("cptCode", "icd10Code", "npiCode", "healthPlanDetails", "billedAmount");

    @PostMapping("/eob/check")
    public ResponseEntity<Object> check(@RequestBody Map<String, Object> data) {
        try {
            // Validate required fields
            String error = validateFields(data, REQUIRED_FIELDS);
            if (error != null) {
                return createErrorResponse(error);
            }

            // Map data to EOBAnalysis model
            EOBAnalysis analysis = mapToEOBAnalysis(data);

            // Placeholder for actual analysis logic
            String analysisResult = "All details match. No discrepancies.";
            List<String> discrepancies = new ArrayList<>();

            // Prepare and return the response
            Map<String, Object> response = new HashMap<>();
            response.put("analysisResult", analysisResult);
            response.put("discrepancies", discrepancies);
            return createSuccessResponse(response);

        } catch (Exception e) {
            return createServerErrorResponse(e);
        }
    }

    // Map request data to EOBAnalysis
    private EOBAnalysis mapToEOBAnalysis(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails((Map<String, Object>) data.get("healthPlanDetails"));
        Double billedAmount = data.get("billedAmount") != null ? Double.valueOf(data.get("billedAmount").toString()) : null;

        return new EOBAnalysis(cptCode, icd10Code, npiCode, healthPlanDetails, billedAmount);
    }
}

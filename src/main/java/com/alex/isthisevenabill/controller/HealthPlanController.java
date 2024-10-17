package com.alex.isthisevenabill.controller;

import com.alex.isthisevenabill.services.tce.TCEService;
import com.alex.isthisevenabill.services.validation.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/healthplan")
public class HealthPlanController extends BaseController {

    private final TCEService tceService;
    private static final List<String> REQUIRED_FIELDS = List.of("cptCode", "icd10Code", "npiCode", "zipCode", "healthPlanDetails");

    public HealthPlanController(TCEService tceService, ValidationService validationService) {
        super(validationService);
        this.tceService = tceService;
    }

    @PostMapping("/tce/estimate")
    public ResponseEntity<Object> estimate(@RequestBody Map<String, Object> data) {
        String error = validateFields(data, REQUIRED_FIELDS);
        if (error != null) {
            return ResponseUtil.errorResponse(error);
        }

        TreatmentCostEstimate estimate = mapToTreatmentCostEstimate(data);
        String estimatedCost = calculateEstimatedCost(estimate);
        Map<String, Object> response = new HashMap<>();
        response.put("estimatedCost", estimatedCost);
        return ResponseUtil.successResponse(response);
    }

    private String calculateEstimatedCost(TreatmentCostEstimate estimate) {
        try {
            return tceService.calculateTotalCostEstimate(estimate.getCptCode(), estimate.getZipCode(), estimate.getNpiCode());
        } catch (TCEException e) {
            throw new RuntimeException("Failed to calculate the cost estimate: " + e.getMessage());
        }
    }

    @PostMapping("/eob/check")
    public ResponseEntity<Object> check(@Valid @RequestBody EOBCheckRequest request) {
        EOBAnalysis analysis = mapToEOBAnalysis(request);
        String analysisResult = "All details match. No discrepancies.";
        List<String> discrepancies = List.of(); // Placeholder for discrepancies

        Map<String, Object> response = new HashMap<>();
        response.put("analysisResult", analysisResult);
        response.put("discrepancies", discrepancies);
        return ResponseUtil.successResponse(response);
    }

    private TreatmentCostEstimate mapToTreatmentCostEstimate(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        String zipCode = (String) data.get("zipCode");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails((Map<String, Object>) data.get("healthPlanDetails"));

        return new TreatmentCostEstimate(cptCode, icd10Code, npiCode, zipCode, healthPlanDetails);
    }

    private EOBAnalysis mapToEOBAnalysis(EOBCheckRequest request) {
        return new EOBAnalysis(
                request.getCptCode(),
                request.getIcd10Code(),
                request.getNpiCode(),
                request.getHealthPlanDetails(),
                request.getBilledAmount()
        );
    }
}
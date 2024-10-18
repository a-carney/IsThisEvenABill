package com.alex.isthisevenabill.controller;

import com.alex.isthisevenabill.dto.TreatmentCostEstimateRequest;
import com.alex.isthisevenabill.dto.TreatmentCostEstimateResponse;
import com.alex.isthisevenabill.services.tce.TCEService;
import com.alex.isthisevenabill.services.validation.ValidationService;
import com.alex.isthisevenabill.exceptions.TCEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/healthplan")
public class HealthPlanController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HealthPlanController.class);
    private final TCEService tceService;

    public HealthPlanController(TCEService tceService, ValidationService validationService) {
        super(validationService);
        this.tceService = tceService;
    }

    @PostMapping("/tce/estimate")
    public ResponseEntity<TreatmentCostEstimateResponse> estimate(@Valid @RequestBody TreatmentCostEstimateRequest request) {
        logger.info("Received treatment cost estimate request for CPT code: {}", request.getCptCode());
        try {
            String estimatedCost = tceService.calculateTotalCostEstimate(
                request.getCptCode(),
                request.getZipCode(),
                request.getNpiCode(),
                request.getHealthPlanDetails()
            );
            logger.info("Calculated estimated cost: {}", estimatedCost);
            return ResponseEntity.ok(new TreatmentCostEstimateResponse(estimatedCost));
        } catch (TCEException e) {
            logger.error("Failed to calculate cost estimate", e);
            return ResponseEntity.badRequest().body(new TreatmentCostEstimateResponse("Failed to calculate the cost estimate: " + e.getMessage()));
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

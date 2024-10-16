package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.common.ResponseUtil;
import com.alex.isthisactuallyabill.dto.EOBCheckRequest;
import com.alex.isthisactuallyabill.models.EOBAnalysis;
import com.alex.isthisactuallyabill.services.validation.ValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
public class EOBCheckerController extends BaseController {

    public EOBCheckerController(ValidationService validationService) {
        super(validationService);
    }

    @PostMapping("/eob/check")
    public ResponseEntity<Object> check(@Valid @RequestBody EOBCheckRequest request) {
        try {
            // Map DTO to EOBAnalysis model
            EOBAnalysis analysis = mapToEOBAnalysis(request);

            // Placeholder for actual analysis logic
            String analysisResult = "All details match. No discrepancies.";
            List<String> discrepancies = new ArrayList<>();

            // Prepare and return the response
            Map<String, Object> response = new HashMap<>();
            response.put("analysisResult", analysisResult);
            response.put("discrepancies", discrepancies);
            return ResponseUtil.successResponse(response);

        } catch (Exception e) {
            return ResponseUtil.errorResponse(e.getMessage());
        }
    }

    // Map DTO to EOBAnalysis
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
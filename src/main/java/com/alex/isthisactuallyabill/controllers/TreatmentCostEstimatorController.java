package com.alex.isthisactuallyabill.view;

import com.alex.isthisactuallyabill.model.HealthPlanDetails;
import com.alex.isthisactuallyabill.model.TreatmentCostEstimate;
import com.alex.isthisactuallyabill.service.TCEService;
import com.alex.isthisactuallyabill.exceptions.TCEException;
import com.alex.isthisactuallyabill.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TreatmentCostEstimatorController {

    private final TCEService tceService;
    private static final List<String> REQUIRED_FIELDS = List.of("cptCode", "icd10Code", "npiCode", "zipCode", "healthPlanDetails");

    public TreatmentCostEstimatorController(TCEService tceService) {
        this.tceService = tceService;
    }

    @PostMapping("/tce/estimate")
    public ResponseEntity<Object> estimate(@RequestBody Map<String, Object> data) {
        try {
            String error = validateFields(data, REQUIRED_FIELDS);
            if (error != null) {
                return ResponseUtil.errorResponse(error);
            }

            TreatmentCostEstimate estimate = mapToTreatmentCostEstimate(data);
            String estimatedCost = tceService.calculateTotalCostEstimate(estimate.getCptCode(), estimate.getZipCode(), estimate.getNpiCode());

            Map<String, Object> response = new HashMap<>();
            response.put("estimatedCost", estimatedCost);
            return ResponseUtil.successResponse(response);

        } catch (TCEException e) {
            return ResponseUtil.errorResponse("Failed to calculate the cost estimate: " + e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e);
        }
    }

    private TreatmentCostEstimate mapToTreatmentCostEstimate(Map<String, Object> data) {
        String cptCode = (String) data.get("cptCode");
        String icd10Code = (String) data.get("icd10Code");
        String npiCode = (String) data.get("npiCode");
        String zipCode = (String) data.get("zipCode");
        HealthPlanDetails healthPlanDetails = mapToHealthPlanDetails((Map<String, Object>) data.get("healthPlanDetails"));

        return new TreatmentCostEstimate(cptCode, icd10Code, npiCode, zipCode, healthPlanDetails);
    }

    private HealthPlanDetails mapToHealthPlanDetails(Map<String, Object> data) {
        if (data == null) {
            return new HealthPlanDetails(null, null, null, null);
        }
        Double deductible = data.get("deductible") != null ? Double.valueOf(data.get("deductible").toString()) : null;
        Double copay = data.get("copay") != null ? Double.valueOf(data.get("copay").toString()) : null;
        Double coinsurance = data.get("coinsurance") != null ? Double.valueOf(data.get("coinsurance").toString()) : null;
        Double outOfPocketMax = data.get("out_of_pocket_max") != null ? Double.valueOf(data.get("out_of_pocket_max").toString()) : null;

        return new HealthPlanDetails(deductible, copay, coinsurance, outOfPocketMax);
    }
}

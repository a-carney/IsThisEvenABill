package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.common.HelperFunctions;
import com.alex.isthisactuallyabill.common.ResponseUtil;
import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public abstract class BaseController {

    /**
     * Validates the request data to ensure all required fields are present.
     *
     * @param data           The request body as a map.
     * @param requiredFields A list of required field names.
     * @return A validation error message if a field is missing, or null if valid.
     */
    protected String validateFields(Map<String, Object> data, Iterable<String> requiredFields) {
        return HelperFunctions.validateRequiredFields(data, requiredFields);
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

    /**
     * Standardizes success response handling.
     *
     * @param data The data to return in the response.
     * @return A success response entity.
     */
    protected ResponseEntity<Object> createSuccessResponse(Object data) {
        return ResponseUtil.successResponse(data);
    }

    /**
     * Standardizes error response handling for validation errors.
     *
     * @param errorMessage The error message.
     * @return A bad request response entity.
     */
    protected ResponseEntity<Object> createErrorResponse(String errorMessage) {
        return ResponseUtil.errorResponse(errorMessage);
    }

    /**
     * Standardizes server error response handling for unexpected exceptions.
     *
     * @param exception The exception to return.
     * @return A server error response entity.
     */
    protected ResponseEntity<Object> createServerErrorResponse(Exception exception) {
        return ResponseUtil.serverErrorResponse(exception);
    }
}

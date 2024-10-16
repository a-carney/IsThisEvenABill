package com.alex.isthisactuallyabill.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class CodeLookupController {

    @GetMapping("/codes/lookup")
    public ResponseEntity<Object> lookupCode(@RequestParam String codeType, @RequestParam String code) {
        try {
            if (codeType == null || code == null) {
                return ResponseUtil.errorResponse(ErrorMessages.INVALID_INPUT.getMessage());
            }

            Map<String, String> params = new HashMap<>();
            params.put("code_type", codeType);
            params.put("code", code);

            Map<String, Object> thirdPartyData = HelperFunctions.makeApiRequest(AppConfig.THIRD_PARTY_API_URL, params);

            if (thirdPartyData == null) {
                return ResponseUtil.errorResponse(ErrorMessages.LOOKUP_FAILED.getMessage());
            }

            Map<String, Object> response = new HashMap<>();
            response.put("code", code);
            response.put("description", thirdPartyData.getOrDefault("description", "No description available"));
            response.put("additionalInfo", thirdPartyData.getOrDefault("additional_info", new HashMap<>()));

            return ResponseUtil.successResponse(response);
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e);
        }
    }
}

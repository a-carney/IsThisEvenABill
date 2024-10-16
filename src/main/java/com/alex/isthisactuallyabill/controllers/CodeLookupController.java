package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.common.ErrorMessages;
import com.alex.isthisactuallyabill.common.ResponseUtil;
import com.alex.isthisactuallyabill.services.CodeLookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class CodeLookupController {

    private final CodeLookupService codeLookupService;

    public CodeLookupController(CodeLookupService codeLookupService) {
        this.codeLookupService = codeLookupService;
    }

    @GetMapping("/codes/lookup")
    public ResponseEntity<Object> lookupCode(@RequestParam(required = true) String codeType,
                                             @RequestParam(required = true) String code) {
        try {
            // Perform the lookup using CodeLookupService
            Map<String, Object> thirdPartyData = codeLookupService.lookupCode(codeType, code);

            if (thirdPartyData == null || thirdPartyData.isEmpty()) {
                return ResponseUtil.errorResponse(ErrorMessages.LOOKUP_FAILED.getMessage());
            }

            // Prepare the response
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

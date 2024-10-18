package com.alex.isthisevenabill.view;

import com.alex.isthisevenabill.common.ErrorMessages;
import com.alex.isthisevenabill.services.CodeLookupService;
import com.alex.isthisevenabill.utils.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
class CodeLookupController {

    private final CodeLookupService service;

    public CodeLookupController(CodeLookupService service) {
        this.service = service;
    }

    @GetMapping("/codes/lookup")
    public ResponseEntity<Object> lookupCode(@RequestParam(required = true) String codeType,
                                             @RequestParam(required = true) String code) {
        try {
            Map<String, Object> thirdPartyData = service.lookupCode(codeType, code);

            if (thirdPartyData == null || thirdPartyData.isEmpty()) {
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

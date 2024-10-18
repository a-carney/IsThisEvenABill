package com.alex.isthisevenabill.controller;

import com.alex.isthisevenabill.common.ErrorMessages;
import com.alex.isthisevenabill.utils.ResponseUtil;
import com.alex.isthisevenabill.services.CodeLookupService;
import com.alex.isthisevenabill.services.medcodes.LookupException;
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
    public ResponseEntity<Object> lookupCode(@RequestParam String codeType, @RequestParam String code) {
        try {
            String result = codeLookupService.lookupCode(codeType, code);
            Map<String, Object> response = createResponse(code, result);
            return ResponseUtil.successResponse(response);
        } catch (LookupException e) {
            return ResponseUtil.errorResponse(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseUtil.errorResponse("Invalid code type: " + codeType);
        } catch (Exception e) {
            return ResponseUtil.serverErrorResponse(e);
        }
    }

    private Map<String, Object> createResponse(String code, String result) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("result", result);
        return response;
    }
}

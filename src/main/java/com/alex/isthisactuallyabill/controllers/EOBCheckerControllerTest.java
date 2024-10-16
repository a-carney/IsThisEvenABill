package com.alex.isthisactuallyabill.controllers;

import com.alex.isthisactuallyabill.dto.EOBCheckRequest;
import com.alex.isthisactuallyabill.models.HealthPlanDetails;
import com.alex.isthisactuallyabill.services.validation.ValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EOBCheckerController.class)
class EOBCheckerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ValidationService validationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCheckSuccess() throws Exception {
        EOBCheckRequest request = new EOBCheckRequest();
        request.setCptCode("99213");
        request.setIcd10Code("I10");
        request.setNpiCode("1234567890");
        request.setHealthPlanDetails(new HealthPlanDetails(1500.0, 20.0, 0.2, 7000.0));
        request.setBilledAmount(100.0);

        Mockito.when(validationService.validateRequiredFields(anyMap(), anyList())).thenReturn(null);

        mockMvc.perform(post("/eob/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.data.analysisResult").value("All details match. No discrepancies."));
    }

    @Test
    void testCheckValidationFailure() throws Exception {
        EOBCheckRequest request = new EOBCheckRequest();
        // Missing required fields

        Mockito.when(validationService.validateRequiredFields(anyMap(), anyList())).thenReturn("Missing required fields: cptCode");

        mockMvc.perform(post("/eob/check")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.errors").isArray());
    }
}
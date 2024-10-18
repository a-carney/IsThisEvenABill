package com.alex.isthisevenabill.services.medcodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CPTLookupServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CPTLookupService cptLookupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cptLookupService = new CPTLookupService();
        cptLookupService.restTemplate = restTemplate;
    }

    @Test
    void testSendSuccess() throws LookupException {
        String code = "99213";
        String expectedResponse = "{\"code\":\"99213\",\"description\":\"Office visit\"}";

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(new ResponseEntity<>(expectedResponse, HttpStatus.OK));

        String actualResponse = cptLookupService.send(code, headers);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testSendFailure() {
        String code = "INVALID_CODE";

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenThrow(new RestClientException("API Error"));

        LookupException exception = assertThrows(LookupException.class, () -> {
            cptLookupService.send(code, headers);
        });

        assertTrue(exception.getMessage().contains("Error occurred while making API call"));
    }
}
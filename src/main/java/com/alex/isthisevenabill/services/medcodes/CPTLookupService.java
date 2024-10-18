package com.alex.isthisevenabill.services.medcodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
public class CPTLookupService implements LookupService {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    private static final String SELECT_FROM_CPT_CODES = "SELECT * FROM cpt_codes WHERE cpt_code = ?";

    @Autowired
    public CPTLookupService(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    @Cacheable(value = "cptCodes", key = "#code")
    public String lookup(String code) throws LookupException {
        check(code);
        try {
            Map<String, Object> result = jdbcTemplate.queryForMap(SELECT_FROM_CPT_CODES, code);
            return process(result);
        } catch (Exception e) {
            throw new LookupException("Error looking up CPT code: " + code, e);
        }
    }

    private String process(Map<String, Object> result) {
        if (result.isEmpty()) {
            return "{\"error\": \"CPT code not found\"}";
        }
        try {
            return objectMapper.writeValueAsString(result);
        } catch (Exception e) {
            return "{\"error\": \"Error processing CPT code data\"}";
        }
    }

    @Override
    public void check(String code) throws LookupException {
        if (code == null || code.isEmpty() || !code.matches("\\d{5}")) {
            throw new LookupException("Invalid CPT code format - must be a 5-digit number");
        }
    }

    @Override
    public HttpHeaders getHeaders() {
        return new HttpHeaders();
    }

    @Override
    public CodeType getCodeType() {
        return CodeType.CPT;
    }
}

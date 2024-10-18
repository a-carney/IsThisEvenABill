package com.alex.isthisevenabill.services.medcodes;

import org.springframework.http.HttpHeaders;
import com.alex.isthisevenabill.services.medcodes.LookupException;

public interface LookupService {
    String execute(String code) throws LookupException;
    
    // Add these methods to the interface
    HttpHeaders getHeaders();
    String process(String response);
    void check(String code) throws LookupException;
    String send(String code, HttpHeaders headers) throws LookupException;
}

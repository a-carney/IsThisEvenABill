package com.alex.isthisevenabill.services.medcodes;

import org.springframework.http.HttpHeaders;
import com.alex.isthisevenabill.services.medcodes.LookupException;

public interface LookupService {
    String lookup(String code) throws LookupException;
    CodeType getCodeType();
    HttpHeaders getHeaders();
    void check(String code) throws LookupException;
}

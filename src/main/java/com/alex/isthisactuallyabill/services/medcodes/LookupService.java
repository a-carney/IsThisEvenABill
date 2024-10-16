package com.alex.isthisactuallyabill.services.medcodes;

public interface LookupService {
    String lookupCode(String code) throws LookupException;
}
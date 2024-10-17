package com.alex.isthisevenabill.services.medcodes;

public interface LookupService {
    String lookupCode(String code) throws LookupException;
}
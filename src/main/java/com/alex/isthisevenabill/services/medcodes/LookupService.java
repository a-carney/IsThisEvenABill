package com.alex.isthisevenabill.services.medcodes;

public interface LookupService {
    String execute(String code) throws LookupException;
}
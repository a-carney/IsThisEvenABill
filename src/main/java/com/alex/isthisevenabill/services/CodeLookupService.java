package com.alex.isthisevenabill.services;

import com.alex.isthisevenabill.services.medcodes.CodeType;
import com.alex.isthisevenabill.services.medcodes.LookupException;
import com.alex.isthisevenabill.services.medcodes.LookupServiceFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CodeLookupService {

    private final LookupServiceFactory lookupServiceFactory;

    public CodeLookupService(LookupServiceFactory lookupServiceFactory) {
        this.lookupServiceFactory = lookupServiceFactory;
    }

    public String lookupCode(String codeType, String code) throws LookupException {
        CodeType type = CodeType.valueOf(codeType.toUpperCase());
        return lookupServiceFactory.getService(type).lookup(code);
    }
}

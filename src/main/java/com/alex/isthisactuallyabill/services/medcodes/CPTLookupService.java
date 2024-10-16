package com.alex.isthisactuallyabill.services.medcodes;

import com.alex.isthisactuallyabill.services.AbstractLookupService;

public class CPTLookupService extends AbstractLookupService {


    public CPTLookupService(String apiUrl) {
        super("https://developer.cms.gov/api/cpt");
    }

    @Override
    public String lookupCode(String code) {
        return makeApiCall(API_EXTERNAL + "?code=" + code);
    }
}


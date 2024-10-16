package com.alex.isthisactuallyabill.services.medcodes;

import com.alex.isthisactuallyabill.services.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
public class ICDLookupService extends AbstractLookupService {

    public ICDLookupService() {
        super("https://clinicaltables.nlm.nih.gov/api/icd10cm/v3/search");
    }

    @Override
    public String lookupCode(String code) {
        return makeApiCall(API_EXTERNAL + "?terms=" + code);
    }


}

package com.alex.isthisactuallyabill.services.medcodes;

import com.alex.isthisactuallyabill.services.AbstractLookupService;
import org.springframework.stereotype.Service;

@Service
class NPILookupService extends AbstractLookupService {

    public NPILookupService() {
        super("https://npiregistry.cms.hhs.gov/api/");
    }

    @Override
    public String lookupCode(String code) {
        String url = API_EXTERNAL + "?number=" + code;
        return makeApiCall(url);
    }

}

package com.alex.isthisevenabill.services.medcodes;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class LookupServiceFactory {

    private final Map<CodeType, LookupService> serviceMap;

    public LookupServiceFactory(List<LookupService> services) {
        serviceMap = new EnumMap<>(CodeType.class);
        services.forEach(service -> serviceMap.put(service.getCodeType(), service));
    }

    public LookupService getService(CodeType codeType) {
        LookupService service = serviceMap.get(codeType);
        if (service == null) {
            throw new IllegalArgumentException("Unsupported code type: " + codeType);
        }
        return service;
    }
}

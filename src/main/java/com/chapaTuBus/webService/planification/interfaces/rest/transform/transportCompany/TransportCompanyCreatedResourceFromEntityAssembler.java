package com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;

public class TransportCompanyCreatedResourceFromEntityAssembler {

    public static TransportCompanyCreatedResource toResourceFromEntity(TransportCompany transportCompany){
        return new TransportCompanyCreatedResource(
                transportCompany.getId(),
                transportCompany.getName(),
                transportCompany.getBusImageUrl(),
                transportCompany.getLogoImageUrl(),
                transportCompany.getDescription()
                );
    }
}

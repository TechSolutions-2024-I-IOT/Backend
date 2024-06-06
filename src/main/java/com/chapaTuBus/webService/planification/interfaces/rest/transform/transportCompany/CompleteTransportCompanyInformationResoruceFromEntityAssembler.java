package com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CompleteTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO.UserDto;

public class CompleteTransportCompanyInformationResoruceFromEntityAssembler {
    public static CompleteTransportCompanyInformationResource toResourceFromEntity(TransportCompany entity) {

        return new CompleteTransportCompanyInformationResource(
            entity.getId(),
            entity.getName(),
            entity.getBusImageUrl(),
                entity.getLogoImageUrl(),
                entity.getDescription(),
                new UserDto(entity.getUser().getId(),entity.getUser().getEmail(),entity.getUser().getRole().getStringName())
        );

    }
}

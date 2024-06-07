package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO.UserDto;

public record CompleteTransportCompanyInformationResource(
        Long id,
        String name,
        String busImageUrl,
        String logoImageUrl,
        String description,
        UserDto user,
        boolean isDeleted
) {
}

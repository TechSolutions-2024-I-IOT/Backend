package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany;

public record TransportCompanyCreatedResource(
        Long id,
        String name,
        String busImageUrl,
        String logoImageUrl,
        String description
){
}

package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany;

public record CreateTransportCompanyResource(
        String name,
        String busImageUrl,
        String logoImageUrl,
        String description
) {
}

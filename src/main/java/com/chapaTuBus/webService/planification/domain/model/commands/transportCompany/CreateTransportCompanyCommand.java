package com.chapaTuBus.webService.planification.domain.model.commands.transportCompany;

public record CreateTransportCompanyCommand(
        Long userId,
        String name,
        String busImageUrl,
        String logoImageUrl,
        String description
){
}

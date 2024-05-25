package com.chapaTuBus.webService.planification.domain.model.commands.transportCompany;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;

public record CreateTransportCompanyCommand(
        Long userId,
        String name,
        String busImageUrl,
        String logoImageUrl,
        String description
){
}

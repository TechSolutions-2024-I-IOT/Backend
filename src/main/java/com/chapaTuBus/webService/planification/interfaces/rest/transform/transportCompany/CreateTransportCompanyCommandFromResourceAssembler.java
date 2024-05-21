package com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;

public class CreateTransportCompanyCommandFromResourceAssembler{

    public static CreateTransportCompanyCommand  toCommand(Long userId,CreateTransportCompanyResource createTransportCompanyResource){

        return new CreateTransportCompanyCommand(
                userId,
                createTransportCompanyResource.name(),
                createTransportCompanyResource.busImageUrl(),
                createTransportCompanyResource.logoImageUrl(),
                createTransportCompanyResource.description()
                );
    }
}

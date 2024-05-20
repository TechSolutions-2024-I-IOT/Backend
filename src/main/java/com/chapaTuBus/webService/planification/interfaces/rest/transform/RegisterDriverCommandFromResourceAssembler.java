package com.chapaTuBus.webService.planification.interfaces.rest.transform;

import com.chapaTuBus.webService.planification.domain.model.commands.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.RegisterDriverResource;

public class RegisterDriverCommandFromResourceAssembler {
    public static RegisterDriverCommand toCommand(RegisterDriverResource registerDriverResource) {
        return new RegisterDriverCommand(registerDriverResource.transportCompanyId(),registerDriverResource.firstName(), registerDriverResource.lastName(), registerDriverResource.driverLicenseNumber(),
                registerDriverResource.dni(), registerDriverResource.photoUrl(), registerDriverResource.phoneNumber(), registerDriverResource.email());
    }
}

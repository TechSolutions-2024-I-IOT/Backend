package com.chapaTuBus.webService.planification.interfaces.rest.transform.driver;

import com.chapaTuBus.webService.planification.domain.model.commands.driver.RegisterDriverCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.RegisterDriverResource;

public class RegisterDriverCommandFromResourceAssembler {
    public static RegisterDriverCommand toCommand(RegisterDriverResource registerDriverResource) {
        return new RegisterDriverCommand(
                registerDriverResource.firstName(),
                registerDriverResource.lastName(),
                registerDriverResource.driverLicenseNumber(),
                registerDriverResource.dni(),
                registerDriverResource.photoUrl(),
                registerDriverResource.phoneNumber(),
                registerDriverResource.email(),
                registerDriverResource.user()
        );
    }
}

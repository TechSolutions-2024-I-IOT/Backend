package com.chapaTuBus.webService.planification.interfaces.rest.transform.driver;

import com.chapaTuBus.webService.planification.domain.model.commands.driver.ModifyDriverCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.ModifyDriverResource;

public class ModifyDriverCommandFromResourceAssembler {

    public static ModifyDriverCommand toCommand(Long driverId,int userId,ModifyDriverResource resource){

        return new ModifyDriverCommand(
                driverId,
                userId,
                resource.firstName(),
                resource.lastName(),
                resource.driverLicenseNumber(),
                resource.dni(),
                resource.photoUrl(),
                resource.phoneNumber(),
                resource.email()
        );

    }
}

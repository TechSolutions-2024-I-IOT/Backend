package com.chapaTuBus.webService.planification.interfaces.rest.transform.driver;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.ModifiedDriverResource;

public class ModifiedDriverResourceFromEntityAssembler {

    public static ModifiedDriverResource toResourceFromEntity(Driver entity){
        return new ModifiedDriverResource(
                entity.getId(),
                entity.getPhotoUrl(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDni(),
                entity.getDriverLicenseNumber(),
                entity.getPhoneNumber(),
                entity.getEmail()
        );
    }
}

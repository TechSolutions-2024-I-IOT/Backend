package com.chapaTuBus.webService.planification.interfaces.rest.transform;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.DriverRegisteredResource;

public class DriverResourceFromEntityAssembler {
    public static DriverRegisteredResource toResourceFromEntity(Driver entity){
        return new DriverRegisteredResource(entity.getId(),entity.getPhotoUrl(), entity.getFirstName(), entity.getLastName(),
                entity.getDni(), entity.getDriverLicenseNumber(), entity.getPhoneNumber(), entity.getEmail());
    }
}

package com.chapaTuBus.webService.planification.interfaces.rest.transform.driver;

import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.DriverInformationResource;


public class DriverInformationResourceFromEntityAssembler {
    public static DriverInformationResource toResourceFromEntity(Driver entity){
        Integer smartBandId = null;
        if(entity.getSmartBand() != null){
            smartBandId = Math.toIntExact(entity.getSmartBand().getId());
        }
        return new DriverInformationResource(
                entity.getId(),
                entity.getPhotoUrl(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDni(),
                entity.getDriverLicenseNumber(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getUser(),
                smartBandId,
                entity.isDeleted()
        );
    }
}

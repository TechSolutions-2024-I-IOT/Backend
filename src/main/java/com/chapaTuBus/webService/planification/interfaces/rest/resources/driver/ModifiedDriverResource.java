package com.chapaTuBus.webService.planification.interfaces.rest.resources.driver;

public record ModifiedDriverResource
        (
                Long driverId,
                String photoUrl,
                String firstName,
                String lastName,
                String dni,
                String driverLicenseNumber,
                String phoneNumber,
                String email,
                boolean isDeleted
        )
{
}

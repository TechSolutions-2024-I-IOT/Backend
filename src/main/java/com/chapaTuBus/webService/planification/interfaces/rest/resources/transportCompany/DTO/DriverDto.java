package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

public record DriverDto
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

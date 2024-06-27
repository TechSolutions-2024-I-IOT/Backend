package com.chapaTuBus.webService.planification.domain.model.commands.driver;

public record ModifyDriverCommand
        (
                Long driverId,
                int userId,
                String firstName,
                String lastName,
                String driverLicenseNumber,
                String dni,
                String photoUrl,
                String phoneNumber,
                String email
         )
{
}

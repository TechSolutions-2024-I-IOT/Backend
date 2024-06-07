package com.chapaTuBus.webService.planification.interfaces.rest.resources.driver;

public record ModifyDriverResource(
        String firstName,
        String lastName,
        String driverLicenseNumber,
        String dni,
        String photoUrl,
        String phoneNumber,
        String email
) {
}

package com.chapaTuBus.webService.planification.interfaces.rest.resources.driver;

public record DriverRegisteredResource (
        Long id,
        String photoUrl,
        String firstName,
        String lastName,
        String dni,
        String driverLicenseNumber,
        String phoneNumber,
        String email,
        int user,
        boolean isDeleted
        ){
}

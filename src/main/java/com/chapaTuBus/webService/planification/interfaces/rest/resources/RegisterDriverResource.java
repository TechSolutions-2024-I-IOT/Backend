package com.chapaTuBus.webService.planification.interfaces.rest.resources;

public record RegisterDriverResource(String photoUrl, String firstName, String lastName, String dni, String driverLicenseNumber, String phoneNumber, String email) {

    public RegisterDriverResource{
        if(driverLicenseNumber == null || driverLicenseNumber.isEmpty()){
            throw new IllegalArgumentException("driverLicenseNumber cannot be null or empty");
        }
        if(dni == null || dni.isEmpty()){
            throw new IllegalArgumentException("dni cannot be null or empty");
        }
        if(firstName == null || firstName.isEmpty()){
            throw new IllegalArgumentException("firstName cannot be null or empty");
        }
        if(lastName == null || lastName.isEmpty()){
            throw new IllegalArgumentException("lastName cannot be null or empty");
        }
    }
}

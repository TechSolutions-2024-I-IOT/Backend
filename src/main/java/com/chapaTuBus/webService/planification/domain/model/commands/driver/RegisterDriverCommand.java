package com.chapaTuBus.webService.planification.domain.model.commands.driver;

public record RegisterDriverCommand(Long transportCompanyId,String firstName, String lastName, String driverLicenseNumber, String dni, String photoUrl,String phoneNumber, String email){

    public RegisterDriverCommand{
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

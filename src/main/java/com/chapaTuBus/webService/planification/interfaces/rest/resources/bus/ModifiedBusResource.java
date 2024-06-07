package com.chapaTuBus.webService.planification.interfaces.rest.resources.bus;

public record ModifiedBusResource(
        Long busId,
        String licensePlate,
        int seatingCapacity,
        int totalCapacity,
        int year,
        String state
){
}

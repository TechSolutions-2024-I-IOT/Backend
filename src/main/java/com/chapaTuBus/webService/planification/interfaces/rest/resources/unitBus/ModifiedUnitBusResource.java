package com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus;

public record ModifiedUnitBusResource (
        int userId,
        Long driverId,
        Long busId
){
}
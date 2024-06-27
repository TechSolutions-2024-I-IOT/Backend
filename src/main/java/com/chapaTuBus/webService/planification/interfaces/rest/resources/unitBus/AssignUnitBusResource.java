package com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus;

public record AssignUnitBusResource(
        int userId,
        Long driverId,
        Long busId
) {
}

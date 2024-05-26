package com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus;

public record AssignUnitBusResource(
        Long transportCompanyId,
        Long driverId,
        Long busId

) {
}

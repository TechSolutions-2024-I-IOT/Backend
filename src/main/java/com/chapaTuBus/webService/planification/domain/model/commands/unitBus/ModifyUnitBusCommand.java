package com.chapaTuBus.webService.planification.domain.model.commands.unitBus;

public record ModifyUnitBusCommand (
        int userId,
        Long unitBusId,
        Long driverId,
        Long busID
){
}

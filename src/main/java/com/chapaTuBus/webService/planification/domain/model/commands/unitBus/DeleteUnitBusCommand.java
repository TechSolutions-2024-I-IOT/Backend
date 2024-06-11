package com.chapaTuBus.webService.planification.domain.model.commands.unitBus;

public record DeleteUnitBusCommand (
        int userId,
        Long unitBusId
) {
}

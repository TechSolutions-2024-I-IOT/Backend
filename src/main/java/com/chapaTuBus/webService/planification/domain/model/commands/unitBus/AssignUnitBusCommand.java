package com.chapaTuBus.webService.planification.domain.model.commands.unitBus;

import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;

public record AssignUnitBusCommand(
        int userId,
        Driver driver,
        Bus bus
) {
}

package com.chapaTuBus.webService.planification.interfaces.rest.resources.bus;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusStates;

public record BusRegisteredResource(
        Long id,
        String licensePlate,
        int seatingCapacity,
        int totalCapacity,
        int year,
        BusStates state,
        int user
) {
}

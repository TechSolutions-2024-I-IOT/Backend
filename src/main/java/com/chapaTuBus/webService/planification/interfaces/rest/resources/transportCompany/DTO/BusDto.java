package com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO;

import com.chapaTuBus.webService.planification.domain.model.valueobjects.BusStates;

public record BusDto
        (
                Long id,
                String licensePlate,
                int seatingCapacity,
                int totalCapacity,
                int year,
                String state,
                boolean isDeleted
        )
{
}

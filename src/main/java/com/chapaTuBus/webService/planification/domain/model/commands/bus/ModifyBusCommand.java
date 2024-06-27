package com.chapaTuBus.webService.planification.domain.model.commands.bus;

public record ModifyBusCommand
        (
                Long busId,
                int userId,
                String licensePlate,
                int seatingCapacity,
                int totalCapacity,
                int year,
                String state
        )
{
}

package com.chapaTuBus.webService.planification.domain.model.commands.Stop;

public record UpdateStopCommand(
        String latitude,
        String longitude,
        String name
) {
}

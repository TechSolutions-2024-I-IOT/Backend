package com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.DTO;

import com.chapaTuBus.webService.planification.domain.model.commands.itinerary.StopCommand;

public record StopDto(
        String name,
        String latitude,
        String longitude
) {
    public StopCommand toCommand(int userId) {
        return new StopCommand(name, latitude, longitude, null, userId);
    }
}

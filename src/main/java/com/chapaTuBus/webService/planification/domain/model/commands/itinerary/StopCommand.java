package com.chapaTuBus.webService.planification.domain.model.commands.itinerary;

public record StopCommand(
        String name,
        String latitude,
        String longitude,
        Long itineraryId,
        int userId
) {

}

package com.chapaTuBus.webService.planification.domain.model.commands.itinerary;

import java.util.List;

public record ModifyItineraryWithStopsCommand(
        Long itineraryId,
        String startTime,
        String endTime,
        List<StopCommand> stops,
        int userId
) {
}

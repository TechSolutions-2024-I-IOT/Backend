package com.chapaTuBus.webService.planification.domain.model.commands.itinerary;

import java.time.LocalTime;
import java.util.List;

public record CreateItineraryWithStopsCommand (
        LocalTime startTime,
        LocalTime endTime,
        List<StopCommand> stops,
        int user
) {
    public CreateItineraryWithStopsCommand {
        if (startTime == null) {
            throw new IllegalArgumentException("StartTime cannot be null");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("EndTime cannot be null");
        }
        if (stops == null || stops.isEmpty()) {
            throw new IllegalArgumentException("Stops cannot be null or empty");
        }
    }
}

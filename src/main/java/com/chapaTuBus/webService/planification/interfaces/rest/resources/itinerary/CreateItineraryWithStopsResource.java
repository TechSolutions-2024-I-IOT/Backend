package com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary;

import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.DTO.StopDto;

import java.util.List;

public record CreateItineraryWithStopsResource(
        String startTime,
        String endTime,
        List<StopDto> stops,
        int user
) {
}

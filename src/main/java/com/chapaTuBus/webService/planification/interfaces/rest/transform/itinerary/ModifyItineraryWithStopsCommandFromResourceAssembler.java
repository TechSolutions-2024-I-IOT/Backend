package com.chapaTuBus.webService.planification.interfaces.rest.transform.itinerary;

import com.chapaTuBus.webService.planification.domain.model.commands.itinerary.ModifyItineraryWithStopsCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.ItineraryWithStopsResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.DTO.StopDto;

import java.util.stream.Collectors;

public class ModifyItineraryWithStopsCommandFromResourceAssembler {
    public static ModifyItineraryWithStopsCommand toCommand(int userId, ItineraryWithStopsResource resource) {
        return new ModifyItineraryWithStopsCommand(
                resource.id(),
                resource.startTime(),
                resource.endTime(),
                resource.stopDto().stream().map(stop -> stop.toCommand(userId)).collect(Collectors.toList()),
                userId
        );
    }
}
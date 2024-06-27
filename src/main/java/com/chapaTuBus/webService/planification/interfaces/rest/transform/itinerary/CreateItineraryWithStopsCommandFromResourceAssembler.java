package com.chapaTuBus.webService.planification.interfaces.rest.transform.itinerary;

import com.chapaTuBus.webService.planification.domain.model.commands.itinerary.CreateItineraryWithStopsCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.itinerary.StopCommand;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.CreateItineraryWithStopsResource;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CreateItineraryWithStopsCommandFromResourceAssembler {
    public static CreateItineraryWithStopsCommand toCommand(CreateItineraryWithStopsResource resource) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<StopCommand> stopCommands = resource.stops().stream()
                .map(dto -> new StopCommand(
                        dto.name(),
                        dto.latitude(),
                        dto.longitude(),
                        null,
                        resource.user()
                ))
                .collect(Collectors.toList());

        return new CreateItineraryWithStopsCommand(
                LocalTime.parse(resource.startTime(), timeFormatter),
                LocalTime.parse(resource.endTime(), timeFormatter),
                stopCommands,
                resource.user()
        );
    }
}


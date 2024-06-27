package com.chapaTuBus.webService.planification.interfaces.rest.transform.itinerary;

import com.chapaTuBus.webService.planification.domain.model.entities.Itinerary;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.DTO.StopDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.ItineraryCreatedResource;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class ItineraryCreatedResourceFromEntityAssembler {
    public static ItineraryCreatedResource toResourceFromEntity(Itinerary itinerary) {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return new ItineraryCreatedResource(
                itinerary.getId(),
                itinerary.getStartTime().format(timeFormatter),
                itinerary.getEndTime().format(timeFormatter),
                itinerary.getStops().stream().map(stop -> new StopDto(
                        stop.getName(),
                        stop.getLatitude(),
                        stop.getLongitude()
                )).collect(Collectors.toList()),
                itinerary.getUser()
        );
    }
}

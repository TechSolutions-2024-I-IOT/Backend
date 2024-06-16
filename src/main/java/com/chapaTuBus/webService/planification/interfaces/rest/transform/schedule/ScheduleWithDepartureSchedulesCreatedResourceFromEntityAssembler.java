package com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule;

import com.chapaTuBus.webService.planification.domain.model.entities.DepartureTime;
import com.chapaTuBus.webService.planification.domain.model.entities.Schedule;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleWithDepartureShedulesCreated;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleWithDepartureSchedulesCreatedResourceFromEntityAssembler {

    public static ScheduleWithDepartureShedulesCreated toResourceFromEntity(Schedule entity) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<DepartureScheduleDto> departureScheduleDtos = entity.getDepartureSchedules().stream()
                .map(ds -> new DepartureScheduleDto(
                        ds.getDepartureTimes().stream()
                                .map(dt -> dt.getTime().format(timeFormatter))
                                .collect(Collectors.toList()),
                        ds.getRoundNumber(),
                        ds.getUnitBus().getId().intValue()
                ))
                .collect(Collectors.toList());

        return new ScheduleWithDepartureShedulesCreated(
                entity.getId(),
                entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                entity.getDescription(),
                departureScheduleDtos,
                entity.getUser()
        );
    }


}

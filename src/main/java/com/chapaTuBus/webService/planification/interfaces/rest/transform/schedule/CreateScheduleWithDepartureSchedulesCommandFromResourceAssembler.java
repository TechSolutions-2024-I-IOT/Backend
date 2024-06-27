package com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule;

import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleWithDepartureSchedulesCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.DepartureScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;
import com.chapaTuBus.webService.planification.domain.model.entities.DepartureTime;
import com.chapaTuBus.webService.planification.domain.model.entities.Schedule;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.CreateScheduleAndDepartureSchedulesResource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
public class CreateScheduleWithDepartureSchedulesCommandFromResourceAssembler {
    public static CreateScheduleWithDepartureSchedulesCommand toCommand(CreateScheduleAndDepartureSchedulesResource resource) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<DepartureScheduleCommand> departureScheduleCommands = resource.departureSchedules().stream()
                .map(dto -> new DepartureScheduleCommand(
                        dto.times().stream()
                                .map(timeStr -> LocalTime.parse(timeStr, timeFormatter))
                                .collect(Collectors.toList()),
                        dto.roundNumber(),
                        (long) dto.unitBusId(),
                        resource.userId()))
                .collect(Collectors.toList());

        return new CreateScheduleWithDepartureSchedulesCommand(
                LocalDate.parse(resource.date(), formatter),
                resource.description(),
                departureScheduleCommands,
                resource.userId()
        );
    }
}
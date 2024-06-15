package com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule;

import com.chapaTuBus.webService.planification.domain.model.entities.Schedule;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleWithUnitBusInformationDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.UnitBusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleWithDepartureSchedulesCompleteInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleWithDepartureShedulesCreated;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleWithDepartureSchedulesCompleteInformationResourceFromEntityAssembler {

    public static ScheduleWithDepartureSchedulesCompleteInformationResource toResourceFromEntity(Schedule entity){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        List<DepartureScheduleWithUnitBusInformationDto> departureSchedules = entity.getDepartureSchedules().stream()
                .map(ds -> new DepartureScheduleWithUnitBusInformationDto(
                        ds.getDepartureTimes().stream()
                                .map(dt -> dt.getTime().format(timeFormatter))
                                .collect(Collectors.toList()),
                        ds.getRoundNumber(),
                        new UnitBusDto(
                                ds.getUnitBus().getId().intValue(),
                                new DriverDto(ds.getUnitBus().getDriver().getId(),ds.getUnitBus().getDriver().getFirstName(),ds.getUnitBus().getDriver().getLastName()),
                                new BusDto(ds.getUnitBus().getBus().getId(),ds.getUnitBus().getBus().getLicensePlate())
                        )
                ))
                .collect(Collectors.toList());


        return new ScheduleWithDepartureSchedulesCompleteInformationResource(
                entity.getId(),
                entity.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                entity.getDescription(),
                departureSchedules,
                entity.getUser(),
                entity.isDeleted()
        );
    }


}

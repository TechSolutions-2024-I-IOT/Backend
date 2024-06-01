package com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule;

import com.chapaTuBus.webService.planification.domain.model.entities.DepartureSchedule;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DTO.ScheduleDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DepartureScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.UnitBusDto;

import java.time.format.DateTimeFormatter;

public class DepartureScheduleCreatedResourceFromEntityAssembler {

    public static DepartureScheduleCreatedResource toResourceFromEntity(DepartureSchedule entity){

        DriverDto driverDto= new DriverDto(
                entity.getUnitBus().getDriver().getId(),
                entity.getUnitBus().getDriver().getFirstName(),
                entity.getUnitBus().getDriver().getLastName());

        BusDto busDto=new BusDto(
                entity.getUnitBus().getBus().getId(),
                entity.getUnitBus().getBus().getLicensePlate());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ScheduleDto scheduleDto= new ScheduleDto(
                entity.getSchedule().getId(),
                entity.getSchedule().getDate().format(formatter),
                entity.getSchedule().getDescription());


        return new DepartureScheduleCreatedResource(
                entity.getId(),
                entity.getRoundNumber(),
                new UnitBusDto(entity.getUnitBus().getId(),driverDto,busDto),
                scheduleDto,
                entity.getUser()
        );
    }
}

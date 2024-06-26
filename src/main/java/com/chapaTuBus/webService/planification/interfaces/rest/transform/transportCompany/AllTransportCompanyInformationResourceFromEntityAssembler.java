package com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.DepartureScheduleWithUnitBusInformationDto;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.AllTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.DTO.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllTransportCompanyInformationResourceFromEntityAssembler {

    public static AllTransportCompanyInformationResource toResourceFromEntity(TransportCompany entity) {

        UserDto user = new UserDto(entity.getUser().getId(), entity.getUser().getEmail(), entity.getUser().getRole().getStringName());

        List<BusDto> buses = entity.getBuses().stream()
                .filter(bus -> !bus.isDeleted())
                .map(actualBus -> new BusDto(
                        actualBus.getId(),
                        actualBus.getLicensePlate(),
                        actualBus.getSeatingCapacity(),
                        actualBus.getTotalCapacity(),
                        actualBus.getYear(),
                        actualBus.getState().name(),
                        actualBus.isDeleted()))
                .collect(Collectors.toList());

        List<DriverDto> drivers = entity.getDrivers().stream()
                .filter(driver -> !driver.isDeleted())
                .map(actualDriver -> new DriverDto(
                        actualDriver.getId(),
                        actualDriver.getPhotoUrl(),
                        actualDriver.getFirstName(),
                        actualDriver.getLastName(),
                        actualDriver.getDni(),
                        actualDriver.getDriverLicenseNumber(),
                        actualDriver.getPhoneNumber(),
                        actualDriver.getEmail(),
                        actualDriver.isDeleted()))
                .collect(Collectors.toList());

        List<UnitBusDto> units = entity.getUnitBuses().stream()
                .filter(unitBus -> !unitBus.isDeleted())
                .map(actualUnitBus -> new UnitBusDto(
                        actualUnitBus.getId(),
                        new DriverForUnitBusDto(
                                actualUnitBus.getDriver().getId(),
                                actualUnitBus.getDriver().getFirstName(),
                                actualUnitBus.getDriver().getLastName()),
                        new BusForUnitBusDto(
                                actualUnitBus.getBus().getId(),
                                actualUnitBus.getBus().getLicensePlate())))
                .collect(Collectors.toList());

        List<ScheduleDto> schedules = entity.getSchedules().stream()
                .filter(schedule -> !schedule.isDeleted())
                .map(actualSchedule -> new ScheduleDto(
                        actualSchedule.getId(),
                        actualSchedule.getDate().format(DateTimeFormatter.ISO_DATE),
                        actualSchedule.getDescription(),
                        actualSchedule.getDepartureSchedules().stream()
                                .filter(departureSchedule -> !departureSchedule.isDeleted())
                                .map(departureSchedule -> new DepartureScheduleWithUnitBusInformationDto(
                                        departureSchedule.getDepartureTimes().stream()
                                                .map(departureTime -> departureTime.getTime().toString())
                                                .collect(Collectors.toList()),
                                        departureSchedule.getRoundNumber(),
                                        new com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.DTO.UnitBusDto(
                                                Math.toIntExact(departureSchedule.getUnitBus().getId()),
                                                new com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.DriverDto(
                                                        departureSchedule.getUnitBus().getDriver().getId(),
                                                        departureSchedule.getUnitBus().getDriver().getFirstName(),
                                                        departureSchedule.getUnitBus().getDriver().getLastName()),
                                                new com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.dto.BusDto(
                                                        departureSchedule.getUnitBus().getBus().getId(),
                                                        departureSchedule.getUnitBus().getBus().getLicensePlate())
                                        )
                                )).collect(Collectors.toList()),
                        actualSchedule.getUser(),
                        actualSchedule.isDeleted()))
                .collect(Collectors.toList());

        return new AllTransportCompanyInformationResource(
                entity.getId(),
                entity.getName(),
                entity.getBusImageUrl(),
                entity.getLogoImageUrl(),
                entity.getDescription(),
                user,
                buses,
                drivers,
                units,
                schedules,
                entity.isDeleted());
    }
}

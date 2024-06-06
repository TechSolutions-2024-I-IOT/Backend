package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDepartureSchedulesByUserIdAndScheduleIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.BusRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.RegisterBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.CreateDepartureScheduleResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DepartureScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.DriverRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.RegisterDriverResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.CreateScheduleResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.AssignUnitBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.BusRegisteredResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.RegisterBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.CreateDepartureScheduleCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.DepartureScheduleCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.DriverResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.RegisterDriverCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule.CreateScheduleCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule.ScheduleCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CreateTransportCompanyCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.TransportCompanyCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus.AssignUnitBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus.UnitBusCreatedResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/transport-company")
public class TransportCompanyController {

    public final TransportCompanyCommandService transportCompanyCommandService;
    public final TransportCompanyQueryService transportCompanyQueryService;
    TransportCompanyController(
            TransportCompanyCommandService transportCompanyCommandService,
            TransportCompanyQueryService transportCompanyQueryService){
        this.transportCompanyCommandService = transportCompanyCommandService;
        this.transportCompanyQueryService = transportCompanyQueryService;
    }

    @GetMapping("/drivers")
    ResponseEntity<List<DriverRegisteredResource>> getDrivers(@RequestParam(name = "userId") int userId){

        var getAllDriversByUserIdQuery= new GetAllDriversByUserIdQuery(userId);

        var drivers= transportCompanyQueryService.handle(getAllDriversByUserIdQuery);

        if(drivers.isEmpty()) return ResponseEntity.notFound().build();

        var driversRegisteredResources=
                drivers.stream().map(
                        DriverResourceFromEntityAssembler::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(driversRegisteredResources);

    }

    @GetMapping("/buses")
    ResponseEntity<List<BusRegisteredResource>>getBuses(@RequestParam(name = "userId") int userId){

        var getAllBusesByUserIdQuery= new GetAllBusesByUserIdQuery(userId);

        var buses= transportCompanyQueryService.handle(getAllBusesByUserIdQuery);

        if(buses.isEmpty())return ResponseEntity.notFound().build();

        var busesRegisteredResources=
                buses.stream().map(
                        BusRegisteredResourceFromEntityAssembler::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(busesRegisteredResources);
    }

    @GetMapping("/unit-buses")
    ResponseEntity<List<UnitBusCreatedResource>>getUnitBuses(@RequestParam(name = "userId") int userId){

        var getAllUnitBusesByUserIdQuery= new GetAllUnitBusesByUserIdQuery(userId);

        var unitBuses= transportCompanyQueryService.handle(getAllUnitBusesByUserIdQuery);

        if(unitBuses.isEmpty())return ResponseEntity.notFound().build();

        var unitBusesRegisteredResource=
                unitBuses.stream().map(
                        UnitBusCreatedResourceFromEntityAssembler ::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(unitBusesRegisteredResource);
    }

    @GetMapping("/departure-schedules")
    ResponseEntity<List<DepartureScheduleCreatedResource>>getDepartureSchedules(
            @RequestParam(name = "userId") int userId, @RequestParam(name="scheduleId") int scheduleId){

        var getAllDepartureSchedulesByUserIdAndScheduleIdQuery= new GetAllDepartureSchedulesByUserIdAndScheduleIdQuery(userId, scheduleId);

        var departureSchedules= transportCompanyQueryService.handle(getAllDepartureSchedulesByUserIdAndScheduleIdQuery);

        if(departureSchedules.isEmpty())return ResponseEntity.notFound().build();

        var departureSchedulesRegisteredResource=
                departureSchedules.stream().map(
                        DepartureScheduleCreatedResourceFromEntityAssembler::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(departureSchedulesRegisteredResource);
    }

    @PostMapping("/new-transport-company")
    ResponseEntity<TransportCompanyCreatedResource> createTransportCompany(@RequestParam(name = "userId") Long userId, @RequestBody CreateTransportCompanyResource createTransportCompanyResource){

        Optional<TransportCompany> transportCompany= transportCompanyCommandService
                .handle(
                        CreateTransportCompanyCommandFromResourceAssembler.toCommand(userId,createTransportCompanyResource)
                        );

        return transportCompany.map(actualTransportCompany->
                new ResponseEntity<>(TransportCompanyCreatedResourceFromEntityAssembler.toResourceFromEntity(actualTransportCompany),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("/register-driver")
    ResponseEntity<DriverRegisteredResource> registerDriver(@RequestBody RegisterDriverResource registerDriverResource){

        Optional<Driver> driver= transportCompanyCommandService.
                //Execute the command
                handle(RegisterDriverCommandFromResourceAssembler.toCommand(registerDriverResource));

        //Return
        return driver.map(actualDriver->
                new ResponseEntity<>(DriverResourceFromEntityAssembler.toResourceFromEntity(actualDriver),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PostMapping("/register-bus")
    ResponseEntity<BusRegisteredResource>registerBus(@RequestBody RegisterBusResource registerBusResource){

        Optional<Bus> bus= transportCompanyCommandService.
                handle(RegisterBusCommandFromResourceAssembler.toCommand(registerBusResource));

        return bus.map(actualBus->
                new ResponseEntity<>(BusRegisteredResourceFromEntityAssembler.toResourceFromEntity(actualBus),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("/register-schedule")
    ResponseEntity<ScheduleCreatedResource>createNewSchedule(@RequestBody CreateScheduleResource createScheduleResource){

        Optional<Schedule> schedule= transportCompanyCommandService.
                handle(CreateScheduleCommandFromResourceAssembler.toCommand(createScheduleResource));

        return schedule.map(actualSchedule->
                new ResponseEntity<>(ScheduleCreatedResourceFromEntityAssembler.toResourceFromEntity(actualSchedule),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("/assign-unit-bus")
    ResponseEntity<UnitBusCreatedResource>assignUnitBus(@RequestBody AssignUnitBusResource assignUnitBusResource){

        Optional<UnitBus> unitBus= transportCompanyCommandService.
                handle(AssignUnitBusCommandFromResourceAssembler.toCommand(assignUnitBusResource));

        return unitBus.map(actualBus->
                new ResponseEntity<>(UnitBusCreatedResourceFromEntityAssembler.toResourceFromEntity(actualBus),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("/register-new-departure-schedule")
    ResponseEntity<DepartureScheduleCreatedResource>createNewDepartureSchedule(@RequestBody CreateDepartureScheduleResource createDepartureScheduleResource){

        Optional<DepartureSchedule> departureSchedule= transportCompanyCommandService.
                handle(CreateDepartureScheduleCommandFromResourceAssembler.toCommand(createDepartureScheduleResource));

        return departureSchedule.map(actualDepartureSchedule->
                new ResponseEntity<>(DepartureScheduleCreatedResourceFromEntityAssembler.toResourceFromEntity(actualDepartureSchedule),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());


    }



}
package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.commands.driver.DeleteDriverCommand;
import com.chapaTuBus.webService.planification.domain.model.commands.schedule.CreateScheduleCommand;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.model.queries.*;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.BusRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.ModifiedBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.ModifyBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.RegisterBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.CreateDepartureScheduleResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DepartureScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.*;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.CreateScheduleResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.ScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CompleteTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.AssignUnitBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.BusRegisteredResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.ModifiedBusResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.ModifyBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.RegisterBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.CreateDepartureScheduleCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.DepartureScheduleCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.*;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule.CreateScheduleCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule.ScheduleCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CompleteTransportCompanyInformationResoruceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CreateTransportCompanyCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.TransportCompanyCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus.AssignUnitBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus.UnitBusCreatedResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/transport-company")
@SecurityRequirement(name="Bearer Authentication")
public class TransportCompanyController {

    public final TransportCompanyCommandService transportCompanyCommandService;
    public final TransportCompanyQueryService transportCompanyQueryService;
    TransportCompanyController(
            TransportCompanyCommandService transportCompanyCommandService,
            TransportCompanyQueryService transportCompanyQueryService){
        this.transportCompanyCommandService = transportCompanyCommandService;
        this.transportCompanyQueryService = transportCompanyQueryService;
    }

    @GetMapping("/all")
    ResponseEntity<List<CompleteTransportCompanyInformationResource>>getAllTransportCompanies(){

        var getAllTransportCompaniesQuery = new GetAllTransportCompaniesQuery();

        var transportCompanies= transportCompanyQueryService.handle(getAllTransportCompaniesQuery);

        var transportCompaniesWithCompleteInformationResoruces= transportCompanies.stream()
                .map(CompleteTransportCompanyInformationResoruceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(transportCompaniesWithCompleteInformationResoruces);

    }

    @GetMapping("/driverById")
    ResponseEntity<DriverRegisteredResource> getDriverById(@RequestParam("driverId") int driverId){

        var getDriverByIdQuery = new GetDriverByIdQuery(driverId);

        var driver= transportCompanyQueryService.handle(getDriverByIdQuery);

        var driverResource= driver.map(DriverResourceFromEntityAssembler::toResourceFromEntity);

        return driverResource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/drivers")
    ResponseEntity<List<DriverRegisteredResource>> getDrivers(@RequestParam(name = "userId") int userId) {

        var getAllDriversByUserIdQuery = new GetAllDriversByUserIdQuery(userId);

        var drivers = transportCompanyQueryService.handle(getAllDriversByUserIdQuery);

        var driversRegisteredResources = drivers.stream()
                .map(DriverResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(driversRegisteredResources);
    }


    @GetMapping("/buses")
    ResponseEntity<List<BusRegisteredResource>> getBuses(@RequestParam(name = "userId") int userId) {

        var getAllBusesByUserIdQuery = new GetAllBusesByUserIdQuery(userId);

        var buses = transportCompanyQueryService.handle(getAllBusesByUserIdQuery);

        var busesRegisteredResources = buses.stream()
                .map(BusRegisteredResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(busesRegisteredResources);
    }


    @GetMapping("/unit-buses")
    ResponseEntity<List<UnitBusCreatedResource>> getUnitBuses(@RequestParam(name = "userId") int userId) {

        var getAllUnitBusesByUserIdQuery = new GetAllUnitBusesByUserIdQuery(userId);

        var unitBuses = transportCompanyQueryService.handle(getAllUnitBusesByUserIdQuery);

        var unitBusesRegisteredResource = unitBuses.stream()
                .map(UnitBusCreatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(unitBusesRegisteredResource);
    }

    @GetMapping("/departure-schedules")
    ResponseEntity<List<DepartureScheduleCreatedResource>> getDepartureSchedules(
            @RequestParam(name = "userId") int userId, @RequestParam(name = "scheduleId") int scheduleId) {

        var getAllDepartureSchedulesByUserIdAndScheduleIdQuery = new GetAllDepartureSchedulesByUserIdAndScheduleIdQuery(userId, scheduleId);

        var departureSchedules = transportCompanyQueryService.handle(getAllDepartureSchedulesByUserIdAndScheduleIdQuery);

        var departureSchedulesRegisteredResource = departureSchedules.stream()
                .map(DepartureScheduleCreatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

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

    @PutMapping("/driver")
    public ResponseEntity<ModifiedDriverResource> modifyDriverByDriverId(
            @RequestParam("driverId") Long driverId,
            @RequestParam("userId") int userId,
            @RequestBody ModifyDriverResource resource) {

        var modifyDriverCommand = ModifyDriverCommandFromResourceAssembler.toCommand(driverId, userId, resource);
        var driver = transportCompanyCommandService.handle(modifyDriverCommand);

        return driver.map(modifiedDriver ->
                        ResponseEntity.ok(ModifiedDriverResourceFromEntityAssembler.toResourceFromEntity(modifiedDriver)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("bus")
    public ResponseEntity<ModifiedBusResource> modifyBusByBusId(
            @RequestParam("busId") Long busId,
            @RequestParam("userId") int userId,
            @RequestBody ModifyBusResource resource){

        var modifyBusCommand= ModifyBusCommandFromResourceAssembler.toCommand(busId,userId,resource);

        var bus = transportCompanyCommandService.handle(modifyBusCommand);

        return bus.map(modifiedBus->
                    ResponseEntity.ok(ModifiedBusResourceFromEntityAssembler.toResourceFromEntity(modifiedBus)))
                .orElseGet(()->ResponseEntity.notFound().build());


    }

    @PatchMapping("/driver/delete")
    public ResponseEntity<String> softDeleteDriver(@RequestBody SoftDeleteDriverResource resource) {
        var deleteDriverCommand = DeleteDriverCommandFromResourceAssemble.toCommand(resource);

        var driverOpt = transportCompanyCommandService.handle(deleteDriverCommand);

        return driverOpt.map(driver -> {
            String message = "El driver de nombre " + driver.getFirstName() + " " + driver.getLastName() + " ha sido correctamente eliminado.";
            return ResponseEntity.ok(message);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
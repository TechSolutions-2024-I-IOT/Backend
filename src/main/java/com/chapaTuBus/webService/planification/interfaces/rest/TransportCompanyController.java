package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;

import com.chapaTuBus.webService.planification.domain.model.entities.*;
import com.chapaTuBus.webService.planification.domain.model.queries.*;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.*;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.CreateDepartureScheduleResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.departureSchedule.DepartureScheduleCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.*;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.CreateItineraryWithStopsResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.itinerary.ItineraryCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.schedule.*;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.AllTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CompleteTransportCompanyInformationResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.*;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.*;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.CreateDepartureScheduleCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.departureSchedule.DepartureScheduleCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.itinerary.CreateItineraryWithStopsCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.*;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.itinerary.ItineraryCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.schedule.*;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.AllTransportCompanyInformationResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CompleteTransportCompanyInformationResoruceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CreateTransportCompanyCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.TransportCompanyCreatedResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.unitBus.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeParseException;
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

    @GetMapping("/byUserId")
    ResponseEntity<CompleteTransportCompanyInformationResource> getTransportCompanyByUserId(@RequestParam("userId") Long userId) {
        var getTransportCompanyByUserIdQuery = new GetTransportCompanyByUserIdQuery(userId);
        var transportCompany = transportCompanyQueryService.handle(getTransportCompanyByUserIdQuery);
        var transportCompanyResource = transportCompany.map(CompleteTransportCompanyInformationResoruceFromEntityAssembler::toResourceFromEntity);
        return transportCompanyResource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/completeInformationById")
    ResponseEntity<AllTransportCompanyInformationResource>getAllTransportCompanyInformation(@RequestParam("transportCompanyId") Long transportCompanyId){

        var getTransportCompanyInformationByIdQuery= new GetTransportCompanyInformationByIdQuery(transportCompanyId);

        var transportCompany = transportCompanyQueryService.handle(getTransportCompanyInformationByIdQuery);

        var allTransportCompanyInformationResource= transportCompany.map(AllTransportCompanyInformationResourceFromEntityAssembler::toResourceFromEntity);

        return allTransportCompanyInformationResource.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());

    }

    @GetMapping("/driverById")
    ResponseEntity<DriverRegisteredResource> getDriverById(@RequestParam("driverId") int driverId){

        var getDriverByIdQuery = new GetDriverByIdQuery(driverId);

        var driver= transportCompanyQueryService.handle(getDriverByIdQuery);

        var driverResource= driver.map(DriverResourceFromEntityAssembler::toResourceFromEntity);

        return driverResource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/scheduleById")
    ResponseEntity<ScheduleWithDepartureSchedulesCompleteInformationResource> getScheduleById(@RequestParam("scheduleId") int scheduleId){

        var getScheduleCompleteInformationByScheduleIdQuery= new GetScheduleCompleteInformationByScheduleIdQuery(scheduleId);

        var schedule= transportCompanyQueryService.handle(getScheduleCompleteInformationByScheduleIdQuery);

        var scheduleResource= schedule.map(ScheduleWithDepartureSchedulesCompleteInformationResourceFromEntityAssembler::toResourceFromEntity);

        return scheduleResource.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping("/drivers")
    ResponseEntity<List<DriverInformationResource>> getDrivers(@RequestParam(name = "userId") int userId) {

        var getAllDriversByUserIdQuery = new GetAllDriversByUserIdQuery(userId);

        var drivers = transportCompanyQueryService.handle(getAllDriversByUserIdQuery);

        var driversRegisteredResources = drivers.stream()
                .map(DriverInformationResourceFromEntityAssembler::toResourceFromEntity)
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
    ResponseEntity<List<UnitBusInformationResource>> getUnitBuses(@RequestParam(name = "userId") int userId) {

        var getAllUnitBusesByUserIdQuery = new GetAllUnitBusesByUserIdQuery(userId);

        var unitBuses = transportCompanyQueryService.handle(getAllUnitBusesByUserIdQuery);

        var unitBusesRegisteredResource = unitBuses.stream()
                .map(UnitBusInformationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(unitBusesRegisteredResource);
    }

    @GetMapping("/schedules")
    ResponseEntity<List<ScheduleCreatedResource>> getSchedules(@RequestParam(name = "userId") int userId){

        var getAllSchedulesByUserIdQuery= new GetAllSchedulesByUserIdQuery(userId);

        var schedules= transportCompanyQueryService.handle(getAllSchedulesByUserIdQuery);

        var schedulesRegisteredResource= schedules.stream()
                .map(ScheduleCreatedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(schedulesRegisteredResource);

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

    @PostMapping("/new-schedule-with-departures")
    public ResponseEntity<?> createScheduleAndDepartureSchedules(@RequestBody CreateScheduleAndDepartureSchedulesResource resource) {
        try {
            Optional<Schedule> schedule = transportCompanyCommandService.handle(
                    CreateScheduleWithDepartureSchedulesCommandFromResourceAssembler.toCommand(resource)
            );

            if (schedule.isPresent()) {
                ScheduleWithDepartureShedulesCreated response = ScheduleWithDepartureSchedulesCreatedResourceFromEntityAssembler.toResourceFromEntity(schedule.get());
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create the schedule, check the provided data.");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Failed to parse date or time: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/new-itinerary-with-stops")
    ResponseEntity<?> createNewDepartureSchedule(@RequestBody CreateItineraryWithStopsResource resource) {
        try {
            Optional<Itinerary> itinerary = transportCompanyCommandService.handle(
                    CreateItineraryWithStopsCommandFromResourceAssembler.toCommand(resource)
            );

            if (itinerary.isPresent()) {
                ItineraryCreatedResource response = ItineraryCreatedResourceFromEntityAssembler.toResourceFromEntity(itinerary.get());
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to create the itinerary, check the provided data.");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Failed to parse date or time: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
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




//    @PutMapping("/schedule-with-departures")
//    public ResponseEntity<?> modifyScheduleAndDepartureSchedules(
//            @RequestParam("userId") int userId,
//            @RequestParam("scheduleId") int scheduleId,
//            @RequestBody ModifyScheduleAndDepartureSchedulesResource resource) {
//
//            var modifyScheduleAndDepartureSchedulesCommand = ModifyScheduleAndDepartureSchedulesCommandFromResourceAssembler.toCommand(userId, scheduleId, resource);
//
//    }


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

    @PutMapping("/unit-bus")
    public ResponseEntity<ModifiedUnitBusResource> modifyUnitBusByUnitBusId(
            @RequestParam("unitBusId") Long unitBusId,
            @RequestParam("userId") int userId,
            @RequestBody ModifyUnitBusResource resource){
       var modifyUnitBusCommand = ModifyUnitBusCommandFromResourceAssembler.toCommand(userId, unitBusId, resource);
       var unitBus = transportCompanyCommandService.handle(modifyUnitBusCommand);
       return unitBus.map(modifiedUnitBus -> ResponseEntity.ok(ModifiedUnitBusResourceFromEntityAssembler.toResourceFromEntity(modifiedUnitBus)))
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/driver/delete")
    public ResponseEntity<String> softDeleteDriver(@RequestBody SoftDeleteDriverResource resource) {
        var deleteDriverCommand = DeleteDriverCommandFromResourceAssemble.toCommand(resource);

        var driverOpt = transportCompanyCommandService.handle(deleteDriverCommand);

        return driverOpt.map(driver -> {
            String message = "El driver de nombre " + driver.getFirstName() + " " + driver.getLastName() + " ha sido correctamente eliminado.";
            return ResponseEntity.ok(message);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("/bus/delete")
    public ResponseEntity<String> softDeleteBus(@RequestBody SoftDeleteBusResource resource){

        var deleteBusCommand= DeleteBusCommandFromResourceAssembler.toCommand(resource);

        var busOpt= transportCompanyCommandService.handle(deleteBusCommand);

        return busOpt.map(bus->{
            String message= "El bus de placa "+bus.getLicensePlate()+" ha sido eliminado correctamente";
            return ResponseEntity.ok(message);
        }).orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PatchMapping("/unit-bus/delete")
    public ResponseEntity<String> softDeleteUnitBus(@RequestBody SoftDeleteUnitBusResource resource){
        var deleteUnitBusCommand = DeleteUnitBusCommandFromResourceAssembler.toCommand(resource);
        var unitBusOpt = transportCompanyCommandService.handle(deleteUnitBusCommand);
        return unitBusOpt.map(unitBus -> {
            String message = "La unidad de bus de id: " + unitBus.getId() + " ha sido eliminado correctamente";
            return ResponseEntity.ok(message);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
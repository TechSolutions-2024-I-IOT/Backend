package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.Bus;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.entities.UnitBus;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByUserIdQuery;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllUnitBusesByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.BusRegisteredResoruce;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.bus.RegisterBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.DriverRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.RegisterDriverResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.AssignUnitBusResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.unitBus.UnitBusCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.BusRegisteredResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.bus.RegisterBusCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.DriverResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.RegisterDriverCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/transport-company/")
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

    @GetMapping("{transportCompanyId}/buses")
    ResponseEntity<List<BusRegisteredResoruce>>getBuses(@PathVariable Long transportCompanyId){

        var getAllBusesByTransportCompanyIdQuery= new GetAllBusesByTransportCompanyIdQuery(transportCompanyId);

        var buses= transportCompanyQueryService.handle(getAllBusesByTransportCompanyIdQuery);

        if(buses.isEmpty())return ResponseEntity.notFound().build();

        var busesRegisteredResources=
                buses.stream().map(
                        BusRegisteredResourceFromEntityAssembler::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(busesRegisteredResources);
    }

    @PostMapping("new-transport-company")
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

    @PostMapping("{transportCompanyId}/register-bus")
    ResponseEntity<BusRegisteredResoruce>registerBus(@PathVariable Long transportCompanyId, @RequestBody RegisterBusResource registerBusResource){

        Optional<Bus> bus= transportCompanyCommandService.
                handle(RegisterBusCommandFromResourceAssembler.toCommand(transportCompanyId,registerBusResource));

        return bus.map(actualBus->
                new ResponseEntity<>(BusRegisteredResourceFromEntityAssembler.toResourceFromEntity(actualBus),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("assign-unit-bus")
    ResponseEntity<UnitBusCreatedResource>assignUnitBus(@RequestBody AssignUnitBusResource assignUnitBusResource){

        Optional<UnitBus> unitBus= transportCompanyCommandService.
                handle(AssignUnitBusCommandFromResourceAssembler.toCommand(assignUnitBusResource));

        return unitBus.map(actualBus->
                new ResponseEntity<>(UnitBusCreatedResourceFromEntityAssembler.toResourceFromEntity(actualBus),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @GetMapping("{transportCompanyId}/unit-buses")
    ResponseEntity<List<UnitBusCreatedResource>>getUnitBuses(@PathVariable Long transportCompanyId){

        var getAllUnitBusesByTransportCompanyIdQuery= new GetAllUnitBusesByTransportCompanyIdQuery(transportCompanyId);

        var unitBuses= transportCompanyQueryService.handle(getAllUnitBusesByTransportCompanyIdQuery);

        if(unitBuses.isEmpty())return ResponseEntity.notFound().build();

        var unitBusesRegisteredResource=
                unitBuses.stream().map(
                        UnitBusCreatedResourceFromEntityAssembler ::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(unitBusesRegisteredResource);
    }

}
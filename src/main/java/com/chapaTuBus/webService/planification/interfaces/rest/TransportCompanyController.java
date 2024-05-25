package com.chapaTuBus.webService.planification.interfaces.rest;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.Driver;
import com.chapaTuBus.webService.planification.domain.model.queries.GetAllDriversByTransportCompanyIdQuery;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyCommandService;
import com.chapaTuBus.webService.planification.domain.services.TransportCompanyQueryService;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.DriverRepository;
import com.chapaTuBus.webService.planification.infraestructure.repositories.jpa.TransportCompanyRepository;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.DriverRegisteredResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.driver.RegisterDriverResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.CreateTransportCompanyResource;
import com.chapaTuBus.webService.planification.interfaces.rest.resources.transportCompany.TransportCompanyCreatedResource;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.DriverResourceFromEntityAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.driver.RegisterDriverCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.CreateTransportCompanyCommandFromResourceAssembler;
import com.chapaTuBus.webService.planification.interfaces.rest.transform.transportCompany.TransportCompanyCreatedResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

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

    @GetMapping("transport-company/{id}/drivers")
    ResponseEntity<List<DriverRegisteredResource>> getDrivers(@PathVariable Long id){

        var getAllDriversByTransportCompanyIdQuery= new GetAllDriversByTransportCompanyIdQuery(id);

        var drivers= transportCompanyQueryService.handle(getAllDriversByTransportCompanyIdQuery);
        if(drivers.isEmpty()) return ResponseEntity.notFound().build();

        var driversRegisteredResources=
                drivers.stream().map(
                        DriverResourceFromEntityAssembler::toResourceFromEntity
                ).toList();

        return ResponseEntity.ok(driversRegisteredResources);

    }

    @PostMapping("transport-company")
    ResponseEntity<TransportCompanyCreatedResource> createTransportCompany(@RequestParam(name = "userId") Long userId, @RequestBody CreateTransportCompanyResource createTransportCompanyResource){

        Optional<TransportCompany> transportCompany= transportCompanyCommandService
                .handle(
                        CreateTransportCompanyCommandFromResourceAssembler.toCommand(userId,createTransportCompanyResource)
                        );

        return transportCompany.map(actualTransportCompany->
                new ResponseEntity<>(TransportCompanyCreatedResourceFromEntityAssembler.toResourceFromEntity(actualTransportCompany),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

    @PostMapping("drivers")
    ResponseEntity<DriverRegisteredResource> registerDriver(@RequestParam (name = "transportCompanyId") Long transportCompanyId, @RequestBody RegisterDriverResource registerDriverResource){


        Optional<Driver> driver= transportCompanyCommandService.
                //Execute the command
                handle(RegisterDriverCommandFromResourceAssembler.toCommand(transportCompanyId,registerDriverResource));

        //Return
        return driver.map(actualDriver->
                new ResponseEntity<>(DriverResourceFromEntityAssembler.toResourceFromEntity(actualDriver),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
}
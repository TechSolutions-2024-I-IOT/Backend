package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.application.internal.commandservices.AuthenticationCommandServiceImpl;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.RegisterUserResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.UserRegisteredResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.RegisterUserCommandFromResourceAssembler;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.UserRegisteredResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {

    private final AuthenticationCommandServiceImpl authenticationCommandService;
    private final RegisterUserCommandFromResourceAssembler assembler; // Inyecta el assembler

    public AuthenticationController(
            AuthenticationCommandServiceImpl authenticationCommandService,
            RegisterUserCommandFromResourceAssembler assembler
    ) {
        this.authenticationCommandService = authenticationCommandService;
        this.assembler = assembler;
    }


    @PostMapping("signUp")
    public ResponseEntity<UserRegisteredResource> signUp(@RequestBody RegisterUserResource registerUserResource) {

        Optional<User> user = authenticationCommandService
                .handle(assembler.toCommandFromResource(registerUserResource));

        return user.map(user1 ->
                new ResponseEntity<>(UserRegisteredResourceFromEntityAssembler.toResourceFromEntity(user1),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());

    }

}

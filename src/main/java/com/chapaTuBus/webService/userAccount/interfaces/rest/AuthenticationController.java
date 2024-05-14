package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.application.internal.commandhandlers.RegisterUserCommandHandler;
import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.interfaces.rest.resources.RegisterUserResource;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.RegisterUserCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {

    private final RegisterUserCommandHandler registerUserCommandHandler;

    public AuthenticationController(RegisterUserCommandHandler registerUserCommandHandler) {
        this.registerUserCommandHandler = registerUserCommandHandler;
    }


    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserResource registerUserResource) {
        RegisterUserCommand command = RegisterUserCommandFromResourceAssembler.toCommandFromResource(registerUserResource);
        registerUserCommandHandler.handle(command);
        return ResponseEntity.ok("User registered successfully");
    }

}

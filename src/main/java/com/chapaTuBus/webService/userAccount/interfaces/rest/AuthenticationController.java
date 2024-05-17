package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.application.internal.commandservices.AuthenticationCommandServiceImpl;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
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
    public ResponseEntity<?> signUp(@RequestBody RegisterUserResource registerUserResource) {
        // Llama al método desde la instancia inyectada
        RegisterUserCommand command = assembler.toCommandFromResource(registerUserResource);
        authenticationCommandService.handle(command);
        return ResponseEntity.ok("User registered successfully");
    }

}

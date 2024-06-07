package com.chapaTuBus.webService.userAccount.interfaces.rest;

import com.chapaTuBus.webService.userAccount.application.internal.commandservices.AuthenticationCommandServiceImpl;
import com.chapaTuBus.webService.userAccount.domain.model.entities.LoginRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.RegisterRequest;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.AuthenticationResponse;
import com.chapaTuBus.webService.userAccount.interfaces.rest.transform.RegisterUserCommandFromResourceAssembler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
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


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request) {
        authenticationCommandService.existsUserByEmail(request);
        authenticationCommandService.validateRegisterRequest(request);
        AuthenticationResponse registeredUser = authenticationCommandService.register(request);
        return new ResponseEntity<AuthenticationResponse>(registeredUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {

        AuthenticationResponse loggedUser = authenticationCommandService.login(request);

        return new ResponseEntity<AuthenticationResponse>(loggedUser, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authenticationCommandService.refreshToken(request, response);
    }
}


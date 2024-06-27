package com.chapaTuBus.webService.userAccount.domain.services;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.LoginRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.RegisterRequest;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public interface AuthenticationCommandService {
    Optional<User> handle(RegisterUserCommand command);
    Optional<User> handle(ModifyProfileCommand command);
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse login(LoginRequest loginRequest);

    void validateRegisterRequest(RegisterRequest registerRequest);
    void existsUserByEmail(RegisterRequest registerRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

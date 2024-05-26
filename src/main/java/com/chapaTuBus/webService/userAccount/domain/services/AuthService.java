package com.chapaTuBus.webService.userAccount.domain.services;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.AuthenticationResponse;
import com.chapaTuBus.webService.userAccount.domain.model.entities.LoginRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthService {

    public abstract AuthenticationResponse register(RegisterRequest registerRequest);

    public abstract AuthenticationResponse login(LoginRequest loginRequest);

    public void validateRegisterRequest(RegisterRequest registerRequest);
    public void existsUserByEmail(RegisterRequest registerRequest);
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}

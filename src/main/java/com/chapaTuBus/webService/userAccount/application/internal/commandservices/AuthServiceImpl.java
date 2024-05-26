package com.chapaTuBus.webService.userAccount.application.internal.commandservices;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.AuthenticationResponse;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.Token;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.entities.LoginRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.RegisterRequest;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.TokenTypes;
import com.chapaTuBus.webService.userAccount.domain.services.AuthService;
import com.chapaTuBus.webService.userAccount.domain.services.JwtService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.TokenRepository;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .userEmail(registerRequest.getUserEmail())
                .userPassword(passwordEncoder.encode(registerRequest.getUserPassword()))
                .role(Roles.USER)
                .build();
        var savedUser = userRepository. save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .user_id(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserEmail(),
                        loginRequest.getUserPassword()));
        var user = userRepository.findByUserEmail(loginRequest.getUserEmail());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .user_id(user.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByUserEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenTypes.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void validateRegisterRequest(RegisterRequest registerRequest)
    {
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email del usuario debe ser obligatorio");
        }
        if (registerRequest.getEmail().length() > 50) {
            throw new IllegalArgumentException("El email del usuario no debe exceder los 50 caracteres");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña del usuario debe ser obligatorio");
        }
        if (registerRequest.getPassword().length() > 100) {
            throw new IllegalArgumentException("La contraseña del usuario no debe exceder los 100 caracteres");
        }
    }

    public void existsUserByEmail(RegisterRequest registerRequest) {
        if (userRepository.existsByUserEmail(registerRequest.getUserEmail())) {
            throw new ValidationException("Ya existe un usuario con el email " + registerRequest.getUserEmail());
        }
    }
}

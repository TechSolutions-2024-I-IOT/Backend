package com.chapaTuBus.webService.userAccount.application.internal.commandservices;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.Token;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.LoginRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Profile;
import com.chapaTuBus.webService.userAccount.domain.model.entities.RegisterRequest;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.AuthenticationResponse;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.TokenTypes;
import com.chapaTuBus.webService.userAccount.domain.services.AuthenticationCommandService;
import com.chapaTuBus.webService.userAccount.domain.services.JwtService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.RoleRepository;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.TokenRepository;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AuthenticationCommandServiceImpl implements AuthenticationCommandService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationCommandServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(RegisterUserCommand command) {

        if(userRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Email already exists");
        }
        var user= new User(command);
        var createdUserResource= userRepository.save(user);

        return Optional.of(createdUserResource);
    }

    private Role getRoleFromResource(String role) {
        try {
            Roles roleType = Roles.valueOf(role);
            // Busca el rol en la base de datos por su tipo
            return roleRepository.findByType(roleType)
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } catch (IllegalArgumentException | NullPointerException e) {
            // Si el rol es inválido o nulo, asigna el rol por defecto
            return roleRepository.findByType(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
        }
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var profile= new Profile(registerRequest.getFirstName(),registerRequest.getLastName());

        var user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(getRoleFromResource(registerRequest.getRole()))
                .profile(profile)
                .build();
        var savedUser = userRepository. save(user);
        var jwtToken = jwtService.generateToken((UserDetails) user);
        var refreshToken = jwtService.generateRefreshToken((UserDetails) user);
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

        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getUserEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String jwtToken = jwtService.generateToken((UserDetails) user);
            String refreshToken = jwtService.generateRefreshToken((UserDetails) user);
            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);
            return AuthenticationResponse.builder()
                    .user_id(user.getId())
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            // Manejo de error si el usuario no es encontrado
            throw new IllegalArgumentException("User not found with email: " + loginRequest.getUserEmail());
        }
    }


    @Override
    public Optional<User> handle(ModifyProfileCommand command) {
        return Optional.empty();
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
            Optional<User> optionalUser = this.userRepository.findByEmail(userEmail);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (jwtService.isTokenValid(refreshToken, user)) {
                    String accessToken = jwtService.generateToken(user);
                    revokeAllUserTokens(user);
                    saveUserToken(user, accessToken);
                    AuthenticationResponse authResponse = AuthenticationResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .build();
                    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
                }
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
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email " + registerRequest.getEmail());
        }
    }
}
package com.chapaTuBus.webService.userAccount.application.internal.commandservices;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;
import com.chapaTuBus.webService.userAccount.domain.services.AuthenticationCommandService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationCommandServiceImpl implements AuthenticationCommandService {

    private final UserRepository userRepository;

    public AuthenticationCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @Override
    public Optional<User> handle(ModifyProfileCommand command) {
        return Optional.empty();
    }
}

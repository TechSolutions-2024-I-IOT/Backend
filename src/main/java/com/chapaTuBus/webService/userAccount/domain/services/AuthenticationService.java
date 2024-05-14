package com.chapaTuBus.webService.userAccount.domain.services;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User signUp(RegisterUserCommand command){
        if(userRepository.existsByEmail(command.email())){
            throw new RuntimeException("Email already exists");
        }
        User user= User.signUp(command);
        return userRepository.save(user);

    }

}

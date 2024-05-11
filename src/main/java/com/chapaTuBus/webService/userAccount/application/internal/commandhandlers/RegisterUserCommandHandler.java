package com.chapaTuBus.webService.userAccount.application.internal.commandhandlers;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.RoleRepository;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;

import java.util.Optional;

public class RegisterUserCommandHandler{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterUserCommandHandler(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }

    public Optional<User> handle(RegisterUserCommand command){
        if(userRepository.existsByEmail(command.email())) throw new RuntimeException("Email logged already exists");

        User user = new User();
        user.setEmail(command.email());
        user.setPassword(command.password());
        userRepository.save(user);
        return userRepository.findByEmail(user.getEmail());
    }


}

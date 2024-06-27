package com.chapaTuBus.webService.userAccount.application.internal.commandservices;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;
import com.chapaTuBus.webService.userAccount.domain.services.UserCommandService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {


    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public Optional<User> handle(ModifyProfileCommand command) {

        var userOpt= userRepository.findById(command.userId());

        if(userOpt.isEmpty())return Optional.empty();

        var user= userOpt.get();
        user.modifyProfile(command);
        userRepository.save(user);

        return Optional.of(user);
    }
}

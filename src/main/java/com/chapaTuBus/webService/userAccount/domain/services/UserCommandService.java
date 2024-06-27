package com.chapaTuBus.webService.userAccount.domain.services;

import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import com.chapaTuBus.webService.userAccount.domain.model.commands.users.ModifyProfileCommand;

import java.util.Optional;

public interface UserCommandService {

    Optional<User> handle(ModifyProfileCommand command);

}

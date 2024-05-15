package com.chapaTuBus.webService.userAccount.domain.model.commands.auth;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;

public record RegisterUserCommand(String email, String password, Role role){

}

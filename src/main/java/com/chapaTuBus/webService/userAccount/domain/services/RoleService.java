package com.chapaTuBus.webService.userAccount.domain.services;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;

public interface RoleService {

    Role save(Role role);

    boolean existByType(Roles type);
}

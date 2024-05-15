package com.chapaTuBus.webService.userAccount.application.internal.commandservices;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import com.chapaTuBus.webService.userAccount.domain.services.RoleService;
import com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public boolean existByType(Roles type) {
        return roleRepository.existsByType(type);
    }
}

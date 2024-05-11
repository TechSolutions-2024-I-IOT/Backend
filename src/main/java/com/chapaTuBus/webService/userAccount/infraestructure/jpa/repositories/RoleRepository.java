package com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName (Roles name);

    boolean existsByName (Roles name);
}

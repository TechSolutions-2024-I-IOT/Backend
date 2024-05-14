package com.chapaTuBus.webService.userAccount.infraestructure.jpa.repositories;

import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByType (Roles name);

    boolean existsByType (Roles name);
}

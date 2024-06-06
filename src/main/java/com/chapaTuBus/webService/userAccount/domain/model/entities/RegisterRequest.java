package com.chapaTuBus.webService.userAccount.domain.model.entities;

import com.chapaTuBus.webService.userAccount.domain.model.valueobjects.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private Roles role;
}

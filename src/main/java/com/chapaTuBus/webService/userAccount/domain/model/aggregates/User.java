package com.chapaTuBus.webService.userAccount.domain.model.aggregates;

import com.chapaTuBus.webService.userAccount.domain.model.commands.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Profile;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Profile profile;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedBy
    @Column(nullable = false)
    private Date updatedAt;


    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


    public User(String email, String password, Role role) {
        this.email=email;
        this.password=password;
        this.role=role;
    }

    public static User signUp(RegisterUserCommand command){
        return new User(command.email(),command.password(),command.role());
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}

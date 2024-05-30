package com.chapaTuBus.webService.userAccount.domain.model.aggregates;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.userAccount.domain.model.commands.auth.RegisterUserCommand;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Profile;
import com.chapaTuBus.webService.userAccount.domain.model.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedBy
    @Column(nullable = false)
    private Date updatedAt;


    @Column(nullable = false, unique = true)
    private String email;

    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transport_company_id",referencedColumnName = "id")
    private TransportCompany transportCompany;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    protected User() {
        this.email= Strings.EMPTY;
        this.password= Strings.EMPTY;
        this.role=null;
        this.transportCompany=new TransportCompany();
    }

    public User(String email, String password, Role role) {
        this.email=email;
        this.password=password;
        this.role=role;
    }

    public User (RegisterUserCommand command){
        this.email=command.email();
        this.password=command.password();
        this.role=command.role();
        this.transportCompany= new TransportCompany();
    }


}

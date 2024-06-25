package com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates;


import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateSmartBandCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterHeartRateLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.HeartRateLog;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "smart_band")
public class SmartBand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToMany(mappedBy = "smartBand",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<HeartRateLog> heartRateLogs;

    private String model;


    private int driverId;

    public SmartBand(){
        this.model= Strings.EMPTY;
        this.heartRateLogs=new ArrayList<>();
    }

    public SmartBand (CreateSmartBandCommand command){
        this.model= command.model();
        this.driverId= Math.toIntExact(command.driverId());
        this.heartRateLogs=new ArrayList<>();
    }

    public HeartRateLog registerNewHeartRateLog(RegisterHeartRateLogCommand command){

        ZonedDateTime nowInPeru = ZonedDateTime.now(ZoneId.of("America/Lima"));
        LocalDateTime localDateTimeInPeru = nowInPeru.toLocalDateTime();

        HeartRateLog heartRateLog= HeartRateLog.builder()
                .heartRate(command.heartRate())
                .smartBand(this)
                .timeStamp(localDateTimeInPeru)
                .build();

        this.heartRateLogs.add(heartRateLog);

        return heartRateLog;
    }

}

//Endpoint crear una smart band y asignarle una smart band a un conductor
//Endpoint meter un nuevo heart rate log a un smart band id mediante solamente su id
//Endpoint obtener listado de heart rates de un smart band mediante el id del smart band
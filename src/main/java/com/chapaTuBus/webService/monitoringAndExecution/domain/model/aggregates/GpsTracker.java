package com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateGPSTrackerCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusLocationLogCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusLocationLog;
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
@Table(name = "gps_tracker")
public class GpsTracker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @OneToMany(mappedBy = "gpsTracker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusLocationLog> busLocationLogs;

    private String model;

    private Long unitBusId;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean isDeleted;

    public GpsTracker(){
        this.model= Strings.EMPTY;
        this.busLocationLogs = new ArrayList<>();
    }

    public GpsTracker (CreateGPSTrackerCommand command){
        this.model = command.model();
        this.unitBusId = (long) Math.toIntExact(command.unitBusId());
        this.busLocationLogs = new ArrayList<>();
    }

    public BusLocationLog registerNewBusLocationLog(RegisterBusLocationLogCommand command){
        ZonedDateTime nowInPeru = ZonedDateTime.now(ZoneId.of("America/Lima"));
        LocalDateTime localDateTimeInPeru = nowInPeru.toLocalDateTime();

        BusLocationLog busLocationLog = BusLocationLog.builder()
                .latitude(command.latitude())
                .longitude(command.longitude())
                .speed(command.speed())
                .gpsTracker(this)
                .timeStamp(localDateTimeInPeru)
                .build();

        this.busLocationLogs.add(busLocationLog);
        return busLocationLog;
    }
}

package com.chapaTuBus.webService.monitoringAndExecution.domain.model.aggregates;

import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.CreateWeightSensorCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.commands.RegisterBusCapacityCommand;
import com.chapaTuBus.webService.monitoringAndExecution.domain.model.entities.BusCapacity;
import com.chapaTuBus.webService.planification.domain.model.commands.transportCompany.CreateTransportCompanyCommand;
import com.chapaTuBus.webService.userAccount.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "weight_sensor")
public class WeightSensor {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @OneToMany(mappedBy = "weightSensor",cascade = CascadeType.ALL,orphanRemoval = true)
        private List<BusCapacity> busCapacities;

        private int unitBusId;

        public WeightSensor(){
                this.busCapacities=new ArrayList<>();
        }

        public WeightSensor(CreateWeightSensorCommand command){
                this.unitBusId= Math.toIntExact(command.unitBusId());
                this.busCapacities=new ArrayList<>();
        }

        public BusCapacity registerNewBusCapacity(RegisterBusCapacityCommand command){

                ZonedDateTime nowInPeru = ZonedDateTime.now(ZoneId.of("America/Lima"));
                LocalDateTime localDateTimeInPeru = nowInPeru.toLocalDateTime();

                BusCapacity busCapacity= BusCapacity.builder()
                        .busCapacity(command.busCapacity())
                        .weightSensor(this)
                        .timeStamp(localDateTimeInPeru)
                        .build();

                this.busCapacities.add(busCapacity);

                return busCapacity;
        }
}

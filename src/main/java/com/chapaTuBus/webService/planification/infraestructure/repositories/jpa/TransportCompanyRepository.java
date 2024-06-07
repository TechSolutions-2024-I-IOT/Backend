package com.chapaTuBus.webService.planification.infraestructure.repositories.jpa;

import com.chapaTuBus.webService.planification.domain.model.aggregates.TransportCompany;
import com.chapaTuBus.webService.planification.domain.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransportCompanyRepository extends JpaRepository<TransportCompany,Long>{

    @Query("SELECT d FROM Driver d WHERE d.user = :userId")
    Optional<Driver> findDriverByUserId(@Param("userId") int userId);


    @Query("SELECT b FROM Bus b WHERE b.user =:userId")
    Optional<Bus> findBusByUserId(@Param("userId") int userId);

    @Query("SELECT s FROM Schedule s JOIN s.transportCompany tc WHERE tc.user.id=:userId")
    Optional<Schedule> findScheduleByUserId(@Param("userId") int userId);

    @Query("SELECT d FROM Driver d JOIN d.transportCompany tc WHERE tc.user.id = :userId AND d.isDeleted = false")
    List<Driver> findDriversByUserId(@Param("userId") int userId);

    @Query("SELECT b FROM Bus b JOIN b.transportCompany tc WHERE tc.user.id = :userId AND b.isDeleted=false")
    List<Bus> findBusesByUserId(@Param("userId") int userId);

    @Query("SELECT s FROM Schedule s JOIN s.transportCompany tc WHERE tc.user.id =:userId AND s.isDeleted=false")
    List<Schedule> findSchedulesByUserId(@Param("userId") int userId);

    @Query("SELECT d FROM Driver d WHERE d.id = :driverId AND d.transportCompany = :transportCompany")
    Optional<Driver> findDriverByIdAndTransportCompany(@Param("driverId") int driverId, @Param("transportCompany") TransportCompany transportCompany);

    @Query("SELECT b FROM Bus b WHERE b.id = :busId AND b.transportCompany = :transportCompany")
    Optional<Bus> findBusById(@Param("busId") int busId, @Param("transportCompany") TransportCompany transportCompany);

    @Query("SELECT u FROM UnitBus u JOIN u.transportCompany tc WHERE tc.user.id=:userId AND u.isDeleted=false")
    List<UnitBus> findUnitBusesByUserId(@Param("userId") int userId);


    @Query("SELECT ds FROM DepartureSchedule ds " +
            "JOIN ds.schedule s " +
            "JOIN s.transportCompany tc " +
            "WHERE tc.user.id = :userId AND s.id = :scheduleId")
    List<DepartureSchedule> findAllDepartureSchedulesByUserIdAndScheduleId(@Param("userId") int userId, @Param("scheduleId") int scheduleId);

    @Query("SELECT d FROM Driver d WHERE d.id=:driverId")
    Optional<Driver> findDriverById(@Param("driverId") int driverId);

    @Query("SELECT b FROM Bus b WHERE b.id=:busId")
    Optional<Bus> findBusById(@Param("busId") int busId);

    Optional<TransportCompany> findByUserId(Long userId);

}
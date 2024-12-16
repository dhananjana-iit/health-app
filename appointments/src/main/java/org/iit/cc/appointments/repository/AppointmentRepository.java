package org.iit.cc.appointments.repository;

import org.iit.cc.appointments.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    // Custom query to find appointments for a specific patient within a time range
    List<Appointment> findByPatientIdAndStartTimeBetween(String patientId, LocalDateTime startTime, LocalDateTime endTime);

    // Custom query to find overlapping appointments
    boolean existsByPatientIdAndStartTimeLessThanAndEndTimeGreaterThan(String patientId, LocalDateTime endTime, LocalDateTime startTime);
}


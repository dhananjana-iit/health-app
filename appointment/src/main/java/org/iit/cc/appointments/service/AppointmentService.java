package org.iit.cc.appointments.service;

import org.bson.types.ObjectId;
import org.iit.cc.appointments.model.Appointment;
import org.iit.cc.appointments.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    /**
     * Schedule a new appointment after validating for conflicts.
     *
     * @param appointment Appointment object to schedule
     * @return Scheduled appointment
     */
    public Appointment scheduleAppointment(Appointment appointment) {
        if (isAppointmentConflict(appointment.getPatientId(), appointment.getStartTime(), appointment.getEndTime())) {
            throw new IllegalStateException("The appointment conflicts with an existing one.");
        }
        return repository.save(appointment);
    }

    /**
     * Retrieve an appointment by ID.
     *
     * @param id Appointment ID
     * @return Optional containing the appointment if found
     */
    public Optional<Appointment> getAppointmentById(String id) {
        return repository.findById(String.valueOf(new ObjectId(id)));
    }

    /**
     * List all appointments for a specific patient within a date range.
     *
     * @param patientId Patient ID
     * @param startTime Start of the date range
     * @param endTime End of the date range
     * @return List of appointments
     */
    public List<Appointment> getAppointmentsForPatient(String patientId, LocalDateTime startTime, LocalDateTime endTime) {
        return repository.findByPatientIdAndStartTimeBetween(patientId, startTime, endTime);
    }

    /**
     * Update an existing appointment by ID.
     *
     * @param id Appointment ID
     * @param updatedAppointment Updated appointment details
     * @return Updated appointment
     */
    public Appointment updateAppointment(String id, Appointment updatedAppointment) {
        Optional<Appointment> existingAppointment = repository.findById(String.valueOf(new ObjectId(id)));
        if (existingAppointment.isEmpty()) {
            throw new IllegalStateException("Appointment with ID " + id + " does not exist.");
        }

        Appointment appointment = existingAppointment.get();
        appointment.setStartTime(updatedAppointment.getStartTime());
        appointment.setEndTime(updatedAppointment.getEndTime());
        appointment.setNotes(updatedAppointment.getNotes());
        appointment.setStatus(updatedAppointment.getStatus());
        appointment.setDoctorId(updatedAppointment.getDoctorId());
        appointment.setDoctorAvailable(updatedAppointment.isDoctorAvailable());

        if (!appointment.isDoctorAvailable()) {
            throw new IllegalStateException("The doctor is not available at the requested time.");
        }

        if (isAppointmentConflict(appointment.getPatientId(), appointment.getStartTime(), appointment.getEndTime())) {
            throw new IllegalStateException("The updated appointment conflicts with an existing one.");
        }

        return repository.save(appointment);
    }

    /**
     * Cancel an appointment by ID.
     *
     * @param id Appointment ID
     */
    public void cancelAppointment(String id) {
        if (!repository.existsById(String.valueOf(new ObjectId(id)))) {
            throw new IllegalStateException("Appointment with ID " + id + " does not exist.");
        }
        repository.deleteById(String.valueOf(new ObjectId(id)));
    }

    /**
     * Check for conflicts with existing appointments.
     *
     * @param patientId Patient ID
     * @param startTime Start time of the new appointment
     * @param endTime End time of the new appointment
     * @return True if a conflict exists, false otherwise
     */
    private boolean isAppointmentConflict(String patientId, LocalDateTime startTime, LocalDateTime endTime) {
        return repository.existsByPatientIdAndStartTimeLessThanAndEndTimeGreaterThan(patientId, endTime, startTime);
    }


}


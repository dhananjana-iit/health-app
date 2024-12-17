package org.iit.cc.appointments.controller;

import org.iit.cc.appointments.model.Appointment;
import org.iit.cc.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    /**
     * Schedule a new appointment.
     *
     * @param appointment Appointment details
     * @return ResponseEntity with scheduled appointment
     */
    @PostMapping
    public ResponseEntity<Appointment> scheduleAppointment(@RequestBody Appointment appointment) {
        Appointment scheduledAppointment = service.scheduleAppointment(appointment);
        return ResponseEntity.ok(scheduledAppointment);
    }

    /**
     * Retrieve an appointment by ID.
     *
     * @param id Appointment ID
     * @return ResponseEntity with appointment details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
        return service.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * List all appointments for a specific patient within a date range.
     *
     * @param patientId Patient ID
     * @param startTime Start time of the range
     * @param endTime End time of the range
     * @return List of appointments
     */
    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentsForPatient(
            @RequestParam String patientId,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        List<Appointment> appointments = service.getAppointmentsForPatient(patientId, startTime, endTime);
        return ResponseEntity.ok(appointments);
    }

    /**
     * Update an appointment by ID.
     *
     * @param id Appointment ID
     * @param updatedAppointment Updated appointment details
     * @return ResponseEntity with updated appointment
     */
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment updatedAppointment) {
        Appointment appointment = service.updateAppointment(id, updatedAppointment);
        return ResponseEntity.ok(appointment);
    }

    /**
     * Cancel an appointment by ID.
     *
     * @param id Appointment ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable String id) {
        service.cancelAppointment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Simple health check endpoint.
     * Used to verify the service is up and running.
     *
     * @return ResponseEntity with OK status
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Appointment Service is UP");
    }

}


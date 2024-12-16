package org.iit.cc.appointments.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;
    private String patientId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // Scheduled, Rescheduled, Canceled, Completed
    private String notes;
    private int doctorId;
    private boolean doctorAvailable;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isDoctorAvailable() {return doctorAvailable;}

    public void setDoctorAvailable(boolean doctorAvailable) {this.doctorAvailable = doctorAvailable;}

    public int getDoctorId() {return doctorId;}

    public void setDoctorId(int doctorId) {this.doctorId = doctorId;}
}


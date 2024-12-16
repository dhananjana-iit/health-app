package org.iit.cc.doctor.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Availability {
    private String dayOfWeek; // Monday, Tuesday, etc.
    private List<TimeSlot> timeSlots;

    // Getters and Setters

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}

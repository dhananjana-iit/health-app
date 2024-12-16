package org.iit.cc.doctor.model;

public class TimeSlot {
    private String startTime; // e.g., 09:00 AM
    private String endTime;   // e.g., 09:30 AM

    // Getters and Setters

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

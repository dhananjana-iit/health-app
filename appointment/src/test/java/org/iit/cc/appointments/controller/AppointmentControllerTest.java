package org.iit.cc.appointments.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.iit.cc.appointments.model.Appointment;
import org.iit.cc.appointments.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService service;

    @Autowired
    private ObjectMapper objectMapper;

    private HttpHeaders getAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes());
        headers.add(HttpHeaders.AUTHORIZATION, basicAuth);
        return headers;
    }

    @Test
    void testScheduleAppointment() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId("1");
        appointment.setPatientId("12345");
        appointment.setStartTime(LocalDateTime.of(2024, 12, 20, 10, 0));
        appointment.setEndTime(LocalDateTime.of(2024, 12, 20, 10, 30));
        appointment.setNotes("Regular Checkup");
        appointment.setStatus("Scheduled");

        Mockito.when(service.scheduleAppointment(any(Appointment.class))).thenReturn(appointment);

        mockMvc.perform(post("/api/appointments")
                        .headers(getAuthHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(appointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.patientId").value("12345"))
                .andExpect(jsonPath("$.status").value("Scheduled"));
    }

    @Test
    void testGetAppointmentById() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId("1");
        appointment.setPatientId("12345");
        appointment.setStartTime(LocalDateTime.of(2024, 12, 20, 10, 0));
        appointment.setEndTime(LocalDateTime.of(2024, 12, 20, 10, 30));
        appointment.setNotes("Regular Checkup");
        appointment.setStatus("Scheduled");

        Mockito.when(service.getAppointmentById("1")).thenReturn(Optional.of(appointment));

        mockMvc.perform(get("/api/appointments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.patientId").value("12345"))
                .andExpect(jsonPath("$.status").value("Scheduled"));
    }

    @Test
    void testGetAppointmentsForPatient() throws Exception {
        Appointment appointment = new Appointment();
        appointment.setId("1");
        appointment.setPatientId("12345");
        appointment.setStartTime(LocalDateTime.of(2024, 12, 20, 10, 0));
        appointment.setEndTime(LocalDateTime.of(2024, 12, 20, 10, 30));
        appointment.setNotes("Regular Checkup");
        appointment.setStatus("Scheduled");

        Mockito.when(service.getAppointmentsForPatient(eq("12345"), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(appointment));

        mockMvc.perform(get("/api/appointments")
                        .headers(getAuthHeaders())
                        .param("patientId", "12345")
                        .param("startTime", "2024-12-20T00:00:00")
                        .param("endTime", "2024-12-20T23:59:59"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].patientId").value("12345"));
    }

    @Test
    void testUpdateAppointment() throws Exception {
        Appointment updatedAppointment = new Appointment();
        updatedAppointment.setId("1");
        updatedAppointment.setPatientId("12345");
        updatedAppointment.setStartTime(LocalDateTime.of(2024, 12, 21, 11, 0));
        updatedAppointment.setEndTime(LocalDateTime.of(2024, 12, 21, 11, 30));
        updatedAppointment.setNotes("Updated Checkup");
        updatedAppointment.setStatus("Rescheduled");

        Mockito.when(service.updateAppointment(eq("1"), any(Appointment.class))).thenReturn(updatedAppointment);

        mockMvc.perform(put("/api/appointments/1")
                        .headers(getAuthHeaders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAppointment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.status").value("Rescheduled"));
    }

    @Test
    void testCancelAppointment() throws Exception {
        Mockito.doNothing().when(service).cancelAppointment("1");

        mockMvc.perform(delete("/api/appointments/1"))
                .andExpect(status().isNoContent());
    }
}


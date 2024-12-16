package org.iit.cc.notificationservice.controller;

import org.iit.cc.notificationservice.model.Notification;
import org.iit.cc.notificationservice.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    private Notification notification;

    @BeforeEach
    public void setUp() {
        notification = new Notification();
        notification.setId("1");
        notification.setPatientId("P123");
        notification.setAppointmentId("A456");
        notification.setType("Reminder");
        notification.setChannel("Email");
        notification.setContent("Your appointment is scheduled for tomorrow.");
        notification.setStatus("Pending");
        notification.setScheduledTime(LocalDateTime.now().plusDays(1));
    }

    @Test
    public void testCreateNotification() throws Exception {
        when(notificationService.createNotification(Mockito.any(Notification.class))).thenReturn(notification);

        mockMvc.perform(post("/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"patientId\":\"P123\"," +
                                "\"appointmentId\":\"A456\"," +
                                "\"type\":\"Reminder\"," +
                                "\"channel\":\"Email\"," +
                                "\"content\":\"Your appointment is scheduled for tomorrow.\"," +
                                "\"status\":\"Pending\"," +
                                "\"scheduledTime\":\"" + LocalDateTime.now().plusDays(1).toString() + "\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.patientId").value("P123"))
                .andExpect(jsonPath("$.status").value("Pending"));
    }

    @Test
    public void testGetNotifications() throws Exception {
        List<Notification> notifications = Arrays.asList(notification);
        when(notificationService.getPendingNotifications()).thenReturn(notifications);

        mockMvc.perform(get("/notifications")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].patientId").value("P123"))
                .andExpect(jsonPath("$[0].status").value("Pending"));
    }
}


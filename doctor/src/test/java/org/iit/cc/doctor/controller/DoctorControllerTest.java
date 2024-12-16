package org.iit.cc.doctor.controller;

import org.iit.cc.doctor.model.Doctor;
import org.iit.cc.doctor.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    private Doctor doctor;

    @BeforeEach
    public void setUp() {
        doctor = new Doctor();
        doctor.setId("1");
        doctor.setName("Dr. John Doe");
        doctor.setSpecialization("Cardiologist");
        doctor.setContact("123-456-7890");
    }

    @Test
    public void testAddDoctor() throws Exception {
        when(doctorService.addDoctor(Mockito.any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Dr. John Doe\"," +
                                "\"specialization\":\"Cardiologist\"," +
                                "\"contact\":\"123-456-7890\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"))
                .andExpect(jsonPath("$.specialization").value("Cardiologist"));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        when(doctorService.getDoctorById("1")).thenReturn(doctor);

        mockMvc.perform(get("/doctors/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"));
    }

    @Test
    public void testGetDoctorsBySpecialization() throws Exception {
        List<Doctor> doctors = Arrays.asList(doctor);
        when(doctorService.getDoctorsBySpecialization("Cardiologist")).thenReturn(doctors);

        mockMvc.perform(get("/doctors")
                        .param("specialization", "Cardiologist")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].specialization").value("Cardiologist"));
    }

    @Test
    public void testUpdateDoctor() throws Exception {
        when(doctorService.updateDoctor(Mockito.eq("1"), Mockito.any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(put("/doctors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Dr. John Doe\"," +
                                "\"specialization\":\"Cardiologist\"," +
                                "\"contact\":\"123-456-7890\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Dr. John Doe"));
    }

    @Test
    public void testDeleteDoctor() throws Exception {
        mockMvc.perform(delete("/doctors/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}

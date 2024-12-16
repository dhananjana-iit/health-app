package org.iit.cc.patienthealthrecord.controller;

import org.iit.cc.patienthealthrecord.model.Patient;
import org.iit.cc.patienthealthrecord.service.PatientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private Patient createMockPatient() {
        Patient patient = new Patient();
        patient.setId("1");
        patient.setName("John Doe");
        patient.setDateOfBirth(LocalDateTime.of(1990, 1, 1, 0, 0));
        patient.setAddress("123 Main St");
        patient.setPhoneNumber(1234567890);
        patient.setDiabetes(true);
        patient.setGender("Male");
        patient.setSmoker(false);
        patient.setPrescriptionNumber(5);
        patient.setLabResultNumber(10);
        return patient;
    }

    @Test
    public void testGetAllPatients() throws Exception {
        Patient patient1 = createMockPatient();
        Patient patient2 = createMockPatient();
        patient2.setId("2");
        patient2.setName("Jane Doe");

        when(patientService.getAllPatients()).thenReturn(Arrays.asList(patient1, patient2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"));

        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    public void testGetPatientById() throws Exception {
        Patient patient = createMockPatient();
        when(patientService.getPatientById("1")).thenReturn(Optional.of(patient));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(patientService, times(1)).getPatientById("1");
    }

    @Test
    public void testCreatePatient() throws Exception {
        Patient patient = createMockPatient();
        when(patientService.createOrUpdatePatient(Mockito.any(Patient.class))).thenReturn(patient);

        String json = "{" +
                "\"name\":\"John Doe\"," +
                "\"dateOfBirth\":\"1990-01-01T00:00:00\"," +
                "\"address\":\"123 Main St\"," +
                "\"phoneNumber\":1234567890," +
                "\"diabetes\":true," +
                "\"gender\":\"Male\"," +
                "\"smoker\":false," +
                "\"prescriptionNumber\":5," +
                "\"labResultNumber\":10}";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(patientService, times(1)).createOrUpdatePatient(Mockito.any(Patient.class));
    }

    @Test
    public void testUpdatePatient() throws Exception {
        Patient patient = createMockPatient();
        when(patientService.createOrUpdatePatient(Mockito.any(Patient.class))).thenReturn(patient);

        String json = "{" +
                "\"name\":\"John Doe\"," +
                "\"dateOfBirth\":\"1990-01-01T00:00:00\"," +
                "\"address\":\"123 Main St\"," +
                "\"phoneNumber\":1234567890," +
                "\"diabetes\":true," +
                "\"gender\":\"Male\"," +
                "\"smoker\":false," +
                "\"prescriptionNumber\":5," +
                "\"labResultNumber\":10}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/patients/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(patientService, times(1)).createOrUpdatePatient(Mockito.any(Patient.class));
    }

    @Test
    public void testDeletePatient() throws Exception {
        doNothing().when(patientService).deletePatient("1");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patients/1"))
                .andExpect(status().isNoContent());

        verify(patientService, times(1)).deletePatient("1");
    }
}


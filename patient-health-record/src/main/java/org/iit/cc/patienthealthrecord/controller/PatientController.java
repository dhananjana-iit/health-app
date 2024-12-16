package org.iit.cc.patienthealthrecord.rest;

import org.iit.cc.patienthealthrecord.entity.Patient;
import org.iit.cc.patienthealthrecord.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public List<Patient> getAllPatients() {
        return service.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        return service.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return service.createOrUpdatePatient(patient);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        return service.createOrUpdatePatient(patient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        service.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

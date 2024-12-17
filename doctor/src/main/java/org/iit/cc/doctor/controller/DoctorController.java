package org.iit.cc.doctor.controller;

import org.iit.cc.doctor.model.Doctor;
import org.iit.cc.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * Add a new doctor.
     *
     * @param doctor Doctor details
     * @return ResponseEntity with the created doctor
     */
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }

    /**
     * Retrieve a doctor by ID.
     *
     * @param id Doctor ID
     * @return ResponseEntity with the doctor details
     */
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    /**
     * Retrieve doctors by specialization.
     *
     * @param specialization Specialization to filter doctors
     * @return ResponseEntity with a list of doctors
     */
    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@RequestParam String specialization) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialization(specialization));
    }

    /**
     * Update doctor details.
     *
     * @param id     Doctor ID
     * @param doctor Updated doctor details
     * @return ResponseEntity with updated doctor details
     */
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(id, doctor));
    }

    /**
     * Delete a doctor by ID.
     *
     * @param id Doctor ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Simple health check endpoint.
     * Used to verify the service is up and running.
     *
     * @return ResponseEntity with OK status and a health message
     */
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Doctor Service is UP");
    }
}

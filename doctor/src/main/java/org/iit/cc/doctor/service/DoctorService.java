package org.iit.cc.doctor.service;

import org.iit.cc.doctor.model.Doctor;
import org.iit.cc.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    /**
     * Add a new doctor.
     *
     * @param doctor Doctor details to add
     * @return The created doctor
     */
    public Doctor addDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    /**
     * Retrieve a doctor by ID.
     *
     * @param id Doctor ID
     * @return Doctor details
     */
    public Doctor getDoctorById(String id) {
        return repository.findById(id).orElseThrow(() -> new IllegalStateException("Doctor not found"));
    }

    /**
     * Retrieve doctors by specialization.
     *
     * @param specialization Specialization to filter by
     * @return List of doctors with the given specialization
     */
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return repository.findBySpecialization(specialization);
    }

    /**
     * Update an existing doctor.
     *
     * @param id Doctor ID
     * @param updatedDoctor Updated doctor details
     * @return Updated doctor
     */
    public Doctor updateDoctor(String id, Doctor updatedDoctor) {
        Doctor existingDoctor = getDoctorById(id);
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
        existingDoctor.setContact(updatedDoctor.getContact());
        existingDoctor.setAvailability(updatedDoctor.getAvailability());
        return repository.save(existingDoctor);
    }

    /**
     * Delete a doctor by ID.
     *
     * @param id Doctor ID
     */
    public void deleteDoctor(String id) {
        repository.deleteById(id);
    }
}


package org.iit.cc.patienthealthrecord.service;

import org.bson.types.ObjectId;
import org.iit.cc.patienthealthrecord.model.Patient;
import org.iit.cc.patienthealthrecord.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
        return repository.findById(id);
    }

    public Patient createOrUpdatePatient(Patient patient) {
        if (patient.getId() != null && repository.existsById(patient.getId())) {
            // Perform an update if the ID exists
            return repository.save(patient);
        } else {
            // Create a new patient if the ID does not exist
            return repository.save(patient);
        }
    }

    public void deletePatient(String id) {
        repository.deleteById(String.valueOf(new ObjectId(id)));
    }
}

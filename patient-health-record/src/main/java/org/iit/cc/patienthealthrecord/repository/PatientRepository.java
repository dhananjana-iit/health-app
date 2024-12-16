package org.iit.cc.patienthealthrecord.repository;

import org.iit.cc.patienthealthrecord.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<Patient, String> {
}

package org.iit.cc.doctor.repository;

import org.iit.cc.doctor.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    List<Doctor> findBySpecialization(String specialization);
}

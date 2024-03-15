package com.example.hdj.api.patient;

import com.example.hdj.api.entity.Hospital;
import com.example.hdj.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    boolean existsByHospitalAndPatientNumber(Hospital hospital, String patientNumber);

    List<Patient> findByIsUseTrue();

    Optional<Patient> findByPatientIdAndIsUseTrue(long patientId);
}

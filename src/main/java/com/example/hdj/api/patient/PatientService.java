package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {
    void postPatient(PatientReqDto patientReqDto);

    @Deprecated
    List<PatientResDto.AllPatient> getPatientAll();

    Page<PatientResDto.AllPatient> getPatientAll(Pageable pageable, PatientReqDto patientReqDto);

    PatientResDto.Patient getPatient(long patientId);

    void putPatient(long patientId, PatientReqDto patientReqDto);

    void delete(long patientId);
}

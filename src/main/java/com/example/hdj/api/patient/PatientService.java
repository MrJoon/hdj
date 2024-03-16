package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;

import java.util.List;

public interface PatientService {
    void postPatient(PatientReqDto patientReqDto);

    @Deprecated
    List<PatientResDto.AllPatient> getPatientAll();

    List<PatientResDto.AllPatient> getPatientAll(PatientReqDto patientReqDto);

    PatientResDto.Patient getPatient(long patientId);

    void putPatient(long patientId, PatientReqDto patientReqDto);

    void delete(long patientId);
}

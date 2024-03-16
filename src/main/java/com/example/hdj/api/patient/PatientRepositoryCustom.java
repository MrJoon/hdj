package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;

import java.util.List;

public interface PatientRepositoryCustom {
    List<PatientResDto.AllPatient> getPatientPageList(PatientReqDto patientReqDto);
}

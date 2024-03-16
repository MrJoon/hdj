package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientRepositoryCustom {
    Page<PatientResDto.AllPatient> getPatientPageList(Pageable pageable, PatientReqDto patientReqDto);
}

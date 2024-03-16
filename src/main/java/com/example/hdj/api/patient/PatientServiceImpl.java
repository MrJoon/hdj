package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;
import com.example.hdj.api.entity.Hospital;
import com.example.hdj.api.entity.Patient;
import com.example.hdj.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Override
    public void postPatient(PatientReqDto patientReqDto) {
        String patientNumber = Utils.randomPatientNumber();
        while (patientRepository.existsByHospitalAndPatientNumber(Hospital.builder().hospitalId(patientReqDto.getHospitalId()).build(), patientNumber)) {
            patientNumber = Utils.randomPatientNumber();
        }

        patientRepository.save(Patient
                .builder()
                .hospital(Hospital.builder().hospitalId(patientReqDto.getHospitalId()).build())
                .patientName(patientReqDto.getPatientName())
                .patientNumber(patientNumber)
                .genderCode(patientReqDto.getGenderCode())
                .birthday(patientReqDto.getBirthday())
                .phoneNumber(patientReqDto.getPhoneNumber())
                .build());
    }

    @Override
    @Deprecated
    public List<PatientResDto.AllPatient> getPatientAll() {
        return Utils.mapList(patientRepository.findByIsUseTrue(), PatientResDto.AllPatient.class);
    }

    @Override
    public Page<PatientResDto.AllPatient> getPatientAll(Pageable pageable, PatientReqDto patientReqDto) {
        return patientRepository.getPatientPageList(pageable, patientReqDto);
    }

    @Override
    public PatientResDto.Patient getPatient(long patientId) {
        return modelMapper.map(findById(patientId)
                , PatientResDto.Patient.class);
    }

    @Override
    public void putPatient(long patientId, PatientReqDto patientReqDto) {
        Patient patient = findById(patientId);
        patient.setPatientName(patientReqDto.getPatientName());
        patient.setGenderCode(patient.getGenderCode());
        patient.setPhoneNumber(patient.getPhoneNumber());
        patientRepository.save(patient);
    }

    @Override
    public void delete(long patientId) {
        Patient patient = findById(patientId);
        patient.setIsUse(false);
        patientRepository.save(patient);
    }

    private Patient findById(long patientId){
        return patientRepository.findByPatientIdAndIsUseTrue(patientId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Not Found"));
    }
}

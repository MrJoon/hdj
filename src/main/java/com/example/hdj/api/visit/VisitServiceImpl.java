package com.example.hdj.api.visit;

import com.example.hdj.api.dto.VisitReqDto;
import com.example.hdj.api.dto.VisitResDto;
import com.example.hdj.api.entity.Hospital;
import com.example.hdj.api.entity.Patient;
import com.example.hdj.api.entity.Visit;
import com.example.hdj.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;
    private final ModelMapper modelMapper;

    @Override
    public void postVisit(VisitReqDto visitReqDto) {
        visitRepository.save(Visit
                .builder()
                .hospital(Hospital.builder().hospitalId(visitReqDto.getHospitalId()).build())
                .patient(Patient.builder().patientId(visitReqDto.getPatientId()).build())
                .visitAt(visitReqDto.getVisitAt())
                .visitStatus(visitReqDto.getVisitStatus())
                .build());
    }

    @Override
    public List<VisitResDto.Visit> getVisitAll() {
        return Utils.mapList(visitRepository.findByIsUseTrue(), VisitResDto.Visit.class);
    }

    @Override
    public VisitResDto.Visit getVisit(long visitId) {
        return modelMapper.map(findById(visitId), VisitResDto.Visit.class);
    }

    @Override
    public void putVisit(long visitId, VisitReqDto visitReqDto) {
        Visit visit = findById(visitId);
        visit.setVisitAt(visitReqDto.getVisitAt());
        visit.setVisitStatus(visitReqDto.getVisitStatus());
    }

    @Override
    public void delete(long visitId) {
        Visit visit = findById(visitId);
        visit.setIsUse(false);
        visitRepository.save(visit);
    }


    private Visit findById(long visitId) {
        return visitRepository.findByVisitIdAndIsUseTrue(visitId).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Not Found"));
    }
}

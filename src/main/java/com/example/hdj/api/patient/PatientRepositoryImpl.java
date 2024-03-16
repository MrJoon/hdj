package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import com.example.hdj.api.dto.PatientResDto;
import com.example.hdj.api.entity.Patient;
import com.example.hdj.api.entity.QPatient;
import com.example.hdj.api.entity.QVisit;
import com.example.hdj.common.HdjQueryDslRepositorySupport;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Repository
@Slf4j
public class PatientRepositoryImpl extends HdjQueryDslRepositorySupport implements PatientRepositoryCustom {

    public PatientRepositoryImpl() {
        super(Patient.class);
    }

    @Override
    public Page<PatientResDto.AllPatient> getPatientPageList(Pageable pageable, PatientReqDto patientReqDto) {
        QPatient patient = QPatient.patient;
        QVisit visit = QVisit.visit;

        JPQLQuery<LocalDateTime> latestVisitSubQuery = new JPAQuery<>(getEntityManager())
                .select(visit.visitAt.max())
                .from(visit)
                .where(visit.patient.patientId.eq(patient.patientId))
                .groupBy(visit.patient.patientId);

        JPQLQuery query = from(patient)
                .select(Projections.constructor(
                        PatientResDto.AllPatient.class, patient.patientId
                        , patient.patientName, patient.patientNumber, patient.genderCode
                        , patient.birthday, patient.phoneNumber, latestVisitSubQuery
                ))
                .where(
                        patientName(patientReqDto)
                        , patientNumber(patientReqDto)
                        , birthday(patientReqDto)
                )
                ;
        QueryResults<PatientResDto.AllPatient> result = getQuerydsl().applyPagination(pageable, query).fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }


    private BooleanExpression patientName(PatientReqDto patientReqDto) {
        if (StringUtils.hasLength(patientReqDto.getPatientName())) {
            return QPatient.patient.patientName.eq(patientReqDto.getPatientName());
        }
        return null;
    }

    private BooleanExpression patientNumber(PatientReqDto patientReqDto) {
        if (StringUtils.hasLength(patientReqDto.getPatientNumber())) {
            return QPatient.patient.patientNumber.eq(patientReqDto.getPatientNumber());
        }
        return null;
    }


    private BooleanExpression birthday(PatientReqDto patientReqDto) {
        if (StringUtils.hasLength(patientReqDto.getBirthday())) {
            return QPatient.patient.birthday.eq(patientReqDto.getBirthday());
        }
        return null;
    }
}
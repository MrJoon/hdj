package com.example.hdj.config;


import com.example.hdj.api.code.CodeRepository;
import com.example.hdj.api.codeGroup.CodeGroupRepository;
import com.example.hdj.api.entity.*;
import com.example.hdj.api.hospital.HospitalRepository;
import com.example.hdj.api.patient.PatientRepository;
import com.example.hdj.api.visit.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


@RequiredArgsConstructor
@Service
public class BootRunner implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final CodeGroupRepository codeGroupRepository;
    private final CodeRepository codeRepository;

    @Override
    public void run(String... args) {
        codeGroupRepository.saveAll(Arrays.asList(
                CodeGroup.builder().codeGroup("성별코드").codeGroupName("성별코드").description("성별을 표시").build()
                , CodeGroup.builder().codeGroup("방문상태코드").codeGroupName("방문상태코드").description("환자방문의 상태").build()
                , CodeGroup.builder().codeGroup("진료과목코드").codeGroupName("진료과목코드").description("진료과").build()
                , CodeGroup.builder().codeGroup("진료유형코드").codeGroupName("진료유형코드").description("진료의 유형").build()
        ));

        codeRepository.saveAll(Arrays.asList(
                Code.builder().codeGroup(CodeGroup.builder().codeGroup("성별코드").build()).code("M").codeName("남").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("성별코드").build()).code("F").codeName("여").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("방문상태코드").build()).code("1").codeName("방문중").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("방문상태코드").build()).code("2").codeName("종료").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("방문상태코드").build()).code("3").codeName("취소").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("진료과목코드").build()).code("01").codeName("내과").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("진료과목코드").build()).code("02").codeName("안과").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("진료유형코드").build()).code("D").codeName("약처방").build()
                , Code.builder().codeGroup(CodeGroup.builder().codeGroup("진료유형코드").build()).code("T").codeName("검사").build()
        ));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Hospital hospital1 =
                hospitalRepository.save(
                        Hospital.builder()
                                .hospitalName("세브란스병원")
                                .nursingHomeNumber("001000101")
                                .director("이세븐")
                                .build());
        Hospital hospital2 =
                hospitalRepository.save(
                        Hospital.builder()
                                .hospitalName("강남삼성병원")
                                .nursingHomeNumber("001000201")
                                .director("이삼성")
                                .build());
        Hospital hospital3 =
                hospitalRepository.save(
                        Hospital.builder()
                                .hospitalName("서울아산병원")
                                .nursingHomeNumber("001000202")
                                .director("정아산")
                                .build());
        Hospital hospital4 =
                hospitalRepository.save(
                        Hospital.builder()
                                .hospitalName("서울대학병원")
                                .nursingHomeNumber("001000102")
                                .director("김서울")
                                .build());
        Patient patient1 =
                patientRepository.save(
                        Patient.builder()
                                .hospital(hospital1)
                                .patientName("김길동")
                                .patientNumber("1111122222001")
                                .genderCode("M")
                                .birthday("1990-01-01")
                                .phoneNumber("010-1234-5678")
                                .build());
        Patient patient2 =
                patientRepository.save(
                        Patient.builder()
                                .hospital(hospital1)
                                .patientName("이아무")
                                .patientNumber("1111122222002")
                                .genderCode("M")
                                .birthday("1995-12-05")
                                .phoneNumber("010-2222-3333")
                                .build());
        Patient patient3 =
                patientRepository.save(
                        Patient.builder()
                                .hospital(hospital2)
                                .patientName("김길동")
                                .patientNumber("1111122222001")
                                .genderCode("M")
                                .birthday("1990-01-01")
                                .phoneNumber("010-1234-5678")
                                .build());
        Patient patient4 =
                patientRepository.save(
                        Patient.builder()
                                .hospital(hospital3)
                                .patientName("김길동")
                                .patientNumber("1111122222001")
                                .genderCode("M")
                                .birthday("1990-01-01")
                                .phoneNumber("010-1234-5678")
                                .build());
        visitRepository.save(
                Visit.builder()
                        .hospital(hospital1)
                        .patient(patient1)
                        .visitAt(LocalDateTime.parse("2024-03-04 09:00:00", formatter))
                        .visitStatus("2")
                        .build());
        visitRepository.save(
                Visit.builder()
                        .hospital(hospital2)
                        .patient(patient3)
                        .visitAt(LocalDateTime.parse("2024-03-05 09:00:00", formatter))
                        .visitStatus("2")
                        .build());
        visitRepository.save(
                Visit.builder()
                        .hospital(hospital3)
                        .patient(patient4)
                        .visitAt(LocalDateTime.parse("2024-03-05 15:30:00", formatter))
                        .visitStatus("2")
                        .build());
        visitRepository.save(
                Visit.builder()
                        .hospital(hospital1)
                        .patient(patient1)
                        .visitAt(LocalDateTime.parse("2024-03-07 09:00:00", formatter))
                        .visitStatus("2")
                        .build());
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        //Close Spring Boot Event
    }
}
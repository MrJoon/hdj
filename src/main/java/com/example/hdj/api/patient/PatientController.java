package com.example.hdj.api.patient;

import com.example.hdj.api.dto.PatientReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.dㅎomain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * 환자 등록
     *
     * @param patientReqDto
     * @return
     */
    @PostMapping(value = "/api/v1/patient", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postPatient(@RequestBody PatientReqDto patientReqDto) {
        patientService.postPatient(patientReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 환자 목록 조회
     *
     * @param pageable
     * @param patientReqDto
     * @return
     */
    @GetMapping(value = "/api/v1/patient")
    public ResponseEntity<?> getPatientAll(@PageableDefault(sort = {"patientId"}, direction = Sort.Direction.DESC, size = 10, page = 1) final Pageable pageable
            , PatientReqDto patientReqDto) {
        return ResponseEntity.ok(patientService.getPatientAll(pageable, patientReqDto));
    }

    /**
     * 환자 조회
     *
     * @param patientId
     * @return
     */
    @GetMapping(value = "/api/v1/patient/{patientId}")
    public ResponseEntity<?> getPatient(@PathVariable long patientId) {
        return ResponseEntity.ok(patientService.getPatient(patientId));
    }

    /**
     * 환자 수정
     *
     * @param patientId
     * @param patientReqDto
     * @return
     */
    @PutMapping(value = "/api/v1/patient/{patientId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putPatient(@PathVariable long patientId, @RequestBody PatientReqDto patientReqDto) {
        patientService.putPatient(patientId, patientReqDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 환자 삭제
     *
     * @param patientId
     * @return
     */
    @DeleteMapping(value = "/api/v1/patient/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable long patientId) {
        patientService.delete(patientId);
        return ResponseEntity.ok().build();
    }
}

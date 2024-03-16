package com.example.hdj.api.visit;

import com.example.hdj.api.dto.VisitReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    /**
     * 환자 방문 등록
     *
     * @param visitReqDto
     * @return
     */
    @PostMapping(value = "/api/v1/visit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postVisit(@RequestBody VisitReqDto visitReqDto) {
        visitService.postVisit(visitReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 환자 방문 목록 조회
     *
     * @return
     */
    @GetMapping(value = "/api/v1/visit")
    public ResponseEntity<?> getVisitAll() {
        return ResponseEntity.ok(visitService.getVisitAll());
    }

    /**
     * 환자 방문 조회
     *
     * @param visitId
     * @return
     */
    @GetMapping(value = "/api/v1/visit/{visitId}")
    public ResponseEntity<?> getVisit(@PathVariable long visitId) {
        return ResponseEntity.ok(visitService.getVisit(visitId));
    }

    /**
     * 환자 방문 수정
     *
     * @param visitId
     * @param visitReqDto
     * @return
     */
    @PutMapping(value = "/api/v1/visit/{visitId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putVisit(@PathVariable long visitId, @RequestBody VisitReqDto visitReqDto) {
        visitService.putVisit(visitId, visitReqDto);
        return ResponseEntity.ok().build();
    }

    /**
     * 환자 방문 삭제
     *
     * @param visitId
     * @return
     */
    @DeleteMapping(value = "/api/v1/visit/{visitId}")
    public ResponseEntity<?> deleteVisit(@PathVariable long visitId) {
        visitService.delete(visitId);
        return ResponseEntity.ok().build();
    }
}

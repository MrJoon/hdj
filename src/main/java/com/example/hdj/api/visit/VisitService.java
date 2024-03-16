package com.example.hdj.api.visit;

import com.example.hdj.api.dto.VisitReqDto;
import com.example.hdj.api.dto.VisitResDto;

import java.util.List;

public interface VisitService {
    void postVisit(VisitReqDto visitReqDto);

    List<VisitResDto.Visit> getVisitAll();

    VisitResDto.Visit getVisit(long visitId);

    void putVisit(long visitId, VisitReqDto visitReqDto);

    void delete(long visitId);
}

package com.example.hdj.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class VisitReqDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 4901441227636675230L;

    private long hospitalId;
    private long patientId;
    private LocalDateTime visitAt;
    private String visitStatus;
}

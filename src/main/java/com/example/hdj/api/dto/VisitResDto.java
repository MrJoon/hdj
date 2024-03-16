package com.example.hdj.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class VisitResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8242151761560386200L;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Visit {
        private long visitId;
        private Hospital hospital;
        private Patient patient;
        private LocalDateTime visitAt;
        private String visitStatus;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Hospital {
        private long hospitalId;
        private String hospitalName;
        private String nursingHomeNumber;
        private String director;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class Patient {
        private long patientId;
        private String patientName;
        private String patientNumber;
        private String genderCode;
        private String birthday;
        private String phoneNumber;
    }
}

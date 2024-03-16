package com.example.hdj.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class PatientResDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -7361419639083437812L;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class AllPatient {
        private long patientId;
        private String patientName;
        private String patientNumber;
        private String genderCode;
        private String birthday;
        private String phoneNumber;
        @JsonIgnore
        private List<Visit> visitList;
        private LocalDateTime lastVisitAt;

        public LocalDateTime getLastVisitAt() {
            if (!visitList.isEmpty()) {
                return visitList.get(visitList.size() - 1).visitAt;
            }
            return lastVisitAt;
        }
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
        private List<Visit> visitList;
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
    public static class Visit {
        private long visitId;
        private LocalDateTime visitAt;
        private String visitStatus;
    }
}

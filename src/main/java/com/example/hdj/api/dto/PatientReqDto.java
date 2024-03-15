package com.example.hdj.api.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PatientReqDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 5409467413522235189L;

    private long hospitalId;
    private String patientName;
    private String patientNumber;
    private String genderCode;
    private String birthday;
    private String phoneNumber;
}

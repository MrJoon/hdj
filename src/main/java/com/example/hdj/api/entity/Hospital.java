package com.example.hdj.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "hospital")
public class Hospital implements Serializable {

    @Serial
    private static final long serialVersionUID = 2031032643731931060L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hospital_id", nullable = false)
    private long hospitalId;

    @Column(name = "hospital_name", length = 45, nullable = false)
    private String hospitalName;

    @Column(name = "nursing_home_number", length = 20, nullable = false)
    private String nursingHomeNumber;

    @Column(name = "director", length = 10, nullable = false)
    private String director;

    @OneToMany(mappedBy = "hospital")
    private List<Patient> patientList;

    @OneToMany(mappedBy = "hospital")
    private List<Visit> visitList;
}

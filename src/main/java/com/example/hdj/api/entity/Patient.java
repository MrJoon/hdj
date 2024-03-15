package com.example.hdj.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"visitList"})
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(name = "ux_patient_001", columnNames = {"patient_id", "hospital_id"})})
public class Patient implements Serializable {

    @Serial
    private static final long serialVersionUID = 2185834917092950047L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id", nullable = false)
    private long patientId;

    @JoinColumn(name = "hospital_id", referencedColumnName = "hospital_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

    @Column(name = "patient_name", length = 45, nullable = false)
    private String patientName;

    @Column(name = "patient_number", length = 13, nullable = false)
    private String patientNumber;

    @Column(name = "gender_code", length = 10, nullable = false)
    private String genderCode;

    @Column(name = "birthday", length = 10)
    private String birthday;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "is_use")
    private Boolean isUse;

    @OneToMany(mappedBy = "patient")
    private List<Visit> visitList;


    @PrePersist
    void prePersist(){
        isUse = true;
    }
}

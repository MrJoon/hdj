package com.example.hdj.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "visit", uniqueConstraints = {@UniqueConstraint(name = "ux_visit_001", columnNames = {"visit_id", "hospital_id", "patient_id"})})
public class Visit implements Serializable {

    @Serial
    private static final long serialVersionUID = -8450131077470092155L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id", nullable = false)
    private long visitId;

    @JoinColumn(name = "hospital_id", referencedColumnName = "hospital_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

    @JoinColumn(name = "patient_id", referencedColumnName = "patient_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @Column(name = "visit_at", nullable = false)
    private LocalDateTime visitAt;

    @Column(name = "visit_status", length = 10, nullable = false)
    private String visitStatus;
}

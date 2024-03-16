package com.example.hdj.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "visit", uniqueConstraints = {@UniqueConstraint(name = "ux_visit_001", columnNames = {"visitId", "hospitalId", "patientId"})})
public class Visit implements Serializable {

    @Serial
    private static final long serialVersionUID = -8450131077470092155L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visitId", nullable = false)
    private long visitId;

    @JoinColumn(name = "hospitalId", referencedColumnName = "hospitalId", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Hospital hospital;

    @JoinColumn(name = "patientId", referencedColumnName = "patientId", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @Column(name = "visitAt", nullable = false)
    private LocalDateTime visitAt;

    @Column(name = "visitStatus", length = 10, nullable = false)
    private String visitStatus;

    @Column(name = "isUse")
    private Boolean isUse;

    @PrePersist
    void prePersist() {
        isUse = true;
    }
}

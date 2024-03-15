package com.example.hdj.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "code")
public class Code implements Serializable {

    @Serial
    private static final long serialVersionUID = -8277308184596221023L;

    @Id
    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @JoinColumn(name = "code_group", referencedColumnName = "code_group", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT), nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private CodeGroup codeGroup;

    @Column(name = "code_name", length = 10, nullable = false)
    private String codeName;
}

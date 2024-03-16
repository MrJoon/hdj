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
@Table(name = "codeGroup")
public class CodeGroup implements Serializable {

    @Serial
    private static final long serialVersionUID = 2227765434741785739L;

    @Id
    @Column(name = "codeGroup", length = 10, nullable = false)
    private String codeGroup;

    @Column(name = "codeGroupName", length = 10, nullable = false)
    private String codeGroupName;


    @Column(name = "description", length = 10, nullable = false)
    private String description;
}

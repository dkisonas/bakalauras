package com.bakalauras.backend.models.lists;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "return_reason")
@Getter
@Setter
public class ReturnReason implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "description", nullable = false)
    private String description;

}
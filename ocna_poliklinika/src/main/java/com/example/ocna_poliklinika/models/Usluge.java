package com.example.ocna_poliklinika.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Usluge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "naziv", nullable = false)
    private String naziv;
    @Column(name = "trajanje", nullable = false)
    private Double trajanje;
    @Column(name = "cijena", nullable = false)
    private Double cijena;
}

package com.example.ocna_poliklinika.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Pacijent")
public class Pacijent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "oib", nullable = false)
    private String oib;
    @Column(name = "adresa", nullable = false)
    private String adresa;
    @Column(name = "kontakt", nullable = false)
    private String kontakt;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @OneToMany(mappedBy = "pacijent", cascade = CascadeType.ALL)
    private List<Termini> termini;


}

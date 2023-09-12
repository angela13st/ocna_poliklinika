package com.example.ocna_poliklinika.models;

import com.example.ocna_poliklinika.models.Doktor;
import com.example.ocna_poliklinika.models.Ordinacija;
import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.models.Usluge;
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
public class Termini {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "datum", nullable = false)
    private String datum;

    @Column(name = "sat", nullable = false)
    private String sat;

    @ManyToOne
    @JoinColumn(name = "idPacijent", nullable = false, referencedColumnName = "id")
    private Pacijent pacijent;

    @ManyToOne
    @JoinColumn(name = "idDoktor", nullable = false, referencedColumnName = "id")
    private Doktor doktor;

    @ManyToOne
    @JoinColumn(name = "idUsluga", nullable = false, referencedColumnName = "id")
    private Usluge usluge;

    @ManyToOne
    @JoinColumn(name = "idOrdinacija", nullable = false, referencedColumnName = "id")
    private Ordinacija ordinacija;
}

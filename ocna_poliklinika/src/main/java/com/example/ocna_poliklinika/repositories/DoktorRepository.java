package com.example.ocna_poliklinika.repositories;

import com.example.ocna_poliklinika.models.Doktor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoktorRepository extends JpaRepository<Doktor, Long> {
    // Dodatne metode za pristup podacima doktora, ako su potrebne
}

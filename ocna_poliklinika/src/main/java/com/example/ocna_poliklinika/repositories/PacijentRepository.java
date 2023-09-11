package com.example.ocna_poliklinika.repositories;

import com.example.ocna_poliklinika.models.Pacijent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacijentRepository extends JpaRepository<Pacijent, Long> {
    Pacijent findByEmail(String email);
    Pacijent findByUsername(String username);
    boolean existsByEmail(String email);

    List<Pacijent> findAll();

    // Dodatne metode za pristup podacima pacijenta
}

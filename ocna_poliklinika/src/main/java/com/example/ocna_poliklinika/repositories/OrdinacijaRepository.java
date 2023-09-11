package com.example.ocna_poliklinika.repositories;

import com.example.ocna_poliklinika.models.Ordinacija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdinacijaRepository extends JpaRepository<Ordinacija, Long> {
}


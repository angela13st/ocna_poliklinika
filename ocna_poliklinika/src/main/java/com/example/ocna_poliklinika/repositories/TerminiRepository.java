package com.example.ocna_poliklinika.repositories;

import com.example.ocna_poliklinika.models.Termini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerminiRepository extends JpaRepository<Termini, Long> {

    List<Termini> findByPacijentId(Long pacijentId);

    List<Termini> findByDoktorId(Long doktorId);
}

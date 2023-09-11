package com.example.ocna_poliklinika.repositories;

import com.example.ocna_poliklinika.models.Usluge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UslugeRepository extends JpaRepository<Usluge, Long> {

}

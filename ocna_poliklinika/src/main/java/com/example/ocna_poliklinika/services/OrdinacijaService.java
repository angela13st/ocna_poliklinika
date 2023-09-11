package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.models.Ordinacija;
import com.example.ocna_poliklinika.repositories.OrdinacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdinacijaService {

    private final OrdinacijaRepository ordinacijaRepository;

    @Autowired
    public OrdinacijaService(OrdinacijaRepository ordinacijaRepository) {
        this.ordinacijaRepository = ordinacijaRepository;
    }

    public List<Ordinacija> getAllOrdinacije() {
        return ordinacijaRepository.findAll();
    }

    public Optional<Ordinacija> getOrdinacijaById(Long id) {
        return ordinacijaRepository.findById(id);
    }

    public Ordinacija createOrdinacija(Ordinacija ordinacija) {
        return ordinacijaRepository.save(ordinacija);
    }

    public void deleteOrdinacija(Long id) {
        ordinacijaRepository.deleteById(id);
    }
}


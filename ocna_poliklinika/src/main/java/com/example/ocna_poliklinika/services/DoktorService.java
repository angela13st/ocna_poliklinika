package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.models.Doktor;
import com.example.ocna_poliklinika.repositories.DoktorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoktorService {

    private final DoktorRepository doktorRepository;

    @Autowired
    public DoktorService(DoktorRepository doktorRepository) {
        this.doktorRepository = doktorRepository;
    }

    public List<Doktor> sviDoktori() {
        return doktorRepository.findAll();
    }

    public Optional<Doktor> dohvatiDoktoraPoId(Long id) {
        return doktorRepository.findById(id);
    }

    public Doktor spremanjeDoktora(Doktor doktor) {
        return doktorRepository.save(doktor);
    }

    public boolean brisanjeDoktora(Long id) {
        doktorRepository.deleteById(id);
        return false;
    }
}

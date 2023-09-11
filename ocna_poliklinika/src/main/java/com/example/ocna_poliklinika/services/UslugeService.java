package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.models.Usluge;
import com.example.ocna_poliklinika.repositories.UslugeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UslugeService {

    private final UslugeRepository uslugeRepository;

    @Autowired
    public UslugeService(UslugeRepository uslugeRepository) {
        this.uslugeRepository = uslugeRepository;
    }

    public List<Usluge> sveUsluge() {
        return uslugeRepository.findAll();
    }

    public Optional<Usluge> dohvatiUsluguPoId(Long id) {
        return uslugeRepository.findById(id);
    }

    public Usluge spremanjeUsluge(Usluge usluge) {
        return uslugeRepository.save(usluge);
    }

    public void brisanjeUsluge(Long id) {
        uslugeRepository.deleteById(id);
    }



    public Usluge spremiNovuUslugu(Usluge usluge) {
        return uslugeRepository.save(usluge);
    }

}

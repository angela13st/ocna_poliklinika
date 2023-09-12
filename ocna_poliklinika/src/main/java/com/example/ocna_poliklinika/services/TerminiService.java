package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.models.Doktor;
import com.example.ocna_poliklinika.models.Termini;
import com.example.ocna_poliklinika.repositories.TerminiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TerminiService {

    private final TerminiRepository terminiRepository;

    @Autowired
    public TerminiService(TerminiRepository terminiRepository) {
        this.terminiRepository = terminiRepository;
    }

    public List<Termini> sviTermini() {
        return terminiRepository.findAll();
    }

    public Optional<Termini> dohvatiTerminPoId(Long id) {
        return terminiRepository.findById(id);
    }

    public Termini spremanjeTermina(Termini termini) {
        return terminiRepository.save(termini);
    }

    public void brisanjeTermina(Long id) {
        terminiRepository.deleteById(id);
    }

    public List<Termini> dohvatiTerminiZaPacijenta(Long pacijentId) {
        return terminiRepository.findByPacijentId(pacijentId);
    }

    public List<Termini> dohvatiTerminiZaDoktora(Long doktorId) {
        return terminiRepository.findByDoktorId(doktorId);
    }

    public boolean imaTermin(Doktor doktor, String datum, String sat) {
        // Prvo dohvatimo sve termine za određenog doktora
        Long doktorId = doktor.getId();
        Iterable<Termini> terminiDoktora = dohvatiTerminiZaDoktora(doktorId);

        // Zatim provjerimo svaki termin da li se poklapa s zadanim datumom i satom
        for (Termini termin : terminiDoktora) {
            if (termin.getDatum().equals(datum) && termin.getSat().equals(sat)) {
                // Doktor već ima termin u to vrijeme
                return true;
            }
        }

        // Doktor nema termin u zadano vrijeme
        return false;
    }
}

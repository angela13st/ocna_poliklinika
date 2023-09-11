package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.repositories.PacijentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacijentService {

    private final PacijentRepository pacijentRepository;

    @Autowired
    public PacijentService(PacijentRepository pacijentRepository) {
        this.pacijentRepository = pacijentRepository;
    }

    public Optional<Pacijent> dohvatiPacijentaPoUsername(String username) {
        return Optional.ofNullable(pacijentRepository.findByUsername(username));
    }

    public Pacijent azurirajPacijenta(Pacijent pacijent) {
        Optional<Pacijent> existingPacijent = pacijentRepository.findById(pacijent.getId());
        if (existingPacijent.isPresent()) {
            Pacijent updatedPacijent = existingPacijent.get();
            updatedPacijent.setIme(pacijent.getIme());
            updatedPacijent.setPrezime(pacijent.getPrezime());
            updatedPacijent.setOib(pacijent.getOib());
            updatedPacijent.setAdresa(pacijent.getAdresa());
            updatedPacijent.setKontakt(pacijent.getKontakt());
            updatedPacijent.setEmail(pacijent.getEmail());
            updatedPacijent.setLozinka(pacijent.getLozinka());

            return pacijentRepository.save(updatedPacijent);
        } else {
            throw new RuntimeException("Pacijent s ID-om " + pacijent.getId() + " nije pronađen.");
        }
    }

    public boolean emailPostoji(String email) {
        Optional<Pacijent> existingPacijent = Optional.ofNullable(pacijentRepository.findByEmail(email));
        return existingPacijent.isPresent();
    }

    public Pacijent registrirajPacijenta(Pacijent pacijent) {
        if (pacijent.getIme() == null) {
            throw new IllegalArgumentException("Ime field cannot be null.");
        }
        if (pacijent.getIme() == null || pacijent.getIme().isEmpty()) {
            throw new IllegalArgumentException("Ime field is required and cannot be null or empty.");
        }
        // You can implement the registration logic here, including checking for existing usernames or emails.
        // After validation, save the new Pacijent to the repository.
        return pacijentRepository.save(pacijent);
    }

    public List<Pacijent> sviPacijenti() {
        return pacijentRepository.findAll();
    }

}

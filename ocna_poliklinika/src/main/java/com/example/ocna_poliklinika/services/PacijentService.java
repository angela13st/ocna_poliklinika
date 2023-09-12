package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.dto.UserLoginRequest;
import com.example.ocna_poliklinika.models.Doktor;
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

    /*public Long findPatientIdByUsername(String username){
        return Pacijent;

    }*/

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
        // Implementirajte logiku registracije ovdje, uključujući provjeru postojećih korisničkih imena ili e-mail adresa.
        // Nakon provjere, spremite novog pacijenta u repozitorij.
        return pacijentRepository.save(pacijent);
    }

    public Pacijent loginUser(UserLoginRequest loginRequest) {
        Pacijent pacijent = pacijentRepository.findByUsername(loginRequest.getUsername());
        if (pacijent != null && pacijent.getLozinka().equals(loginRequest.getLozinka())) {
            return pacijent;
        }
        return null;
    }

    public List<Pacijent> sviPacijenti() {
        return pacijentRepository.findAll();
    }

    public boolean prijava(String username, String lozinka) {
        Optional<Pacijent> pacijentOptional = dohvatiPacijentaPoUsername(username);
        if (pacijentOptional.isPresent()) {
            Pacijent pacijent = pacijentOptional.get();
            // Provjerite lozinku
            if (lozinka.equals(pacijent.getLozinka())) {
                return true; // Lozinke se podudaraju
            }
        }
        return false; // Korisničko ime ili lozinka nisu ispravni
    }
}

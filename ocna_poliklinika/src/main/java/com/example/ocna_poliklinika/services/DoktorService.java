package com.example.ocna_poliklinika.services;

import com.example.ocna_poliklinika.dto.UserLoginRequest;
import com.example.ocna_poliklinika.models.Doktor;
import com.example.ocna_poliklinika.models.Pacijent;
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

    public Doktor loginUser(UserLoginRequest loginRequest) {
        Doktor doktor = doktorRepository.findByUsername(loginRequest.getUsername());
        if (doktor != null && doktor.getLozinka().equals(loginRequest.getLozinka())) {
            return doktor;
        }
        return null;
    }

    public boolean prijava(String username, String lozinka) {
        Optional<Doktor> doktorOptional = Optional.ofNullable(doktorRepository.findByUsername(username));
        if (doktorOptional.isPresent()) {
            Doktor doktor = doktorOptional.get();
            // Provjerite lozinku
            if (lozinka.equals(doktor.getLozinka())) {
                return true; // Lozinke se podudaraju
            }
        }
        return false; // Korisničko ime ili lozinka nisu ispravni
    }
    public boolean brisanjeDoktora(Long id) {
        doktorRepository.deleteById(id);
        return false;
    }
}

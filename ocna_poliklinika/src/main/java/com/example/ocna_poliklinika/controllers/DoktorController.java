package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Doktor;
import com.example.ocna_poliklinika.services.DoktorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doktori")
public class DoktorController {

    private final DoktorService doktorService;

    @Autowired
    public DoktorController(DoktorService doktorService) {
        this.doktorService = doktorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Doktor>> sviDoktori() {
        List<Doktor> doktori = doktorService.sviDoktori();
        if (doktori.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Vraća status 404 ako nema doktora
        }
        return new ResponseEntity<>(doktori, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Doktor>> dohvatiDoktoraPoId(@PathVariable Long id) {
        Optional<Doktor> doktor = doktorService.dohvatiDoktoraPoId(id);
        if (doktor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Vraća status 404 ako doktor nije pronađen
        }
        return new ResponseEntity<>(doktor, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> spremanjeDoktora(@RequestBody Doktor doktor) {
        try {
            Doktor spremljeniDoktor = doktorService.spremanjeDoktora(doktor);
            return ResponseEntity.ok(spremljeniDoktor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom spremanja doktora: " + e.getMessage());
        }
    }





    @DeleteMapping("/{id}")
    public ResponseEntity<Void> brisanjeDoktora(@PathVariable Long id) {
        boolean obrisan = doktorService.brisanjeDoktora(id);
        if (obrisan) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Vraća status 204 ako je doktor uspješno obrisan
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Vraća status 404 ako doktor nije pronađen za brisanje
    }
}

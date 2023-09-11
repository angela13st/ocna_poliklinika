package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Ordinacija;
import com.example.ocna_poliklinika.services.OrdinacijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordinacije")
public class OrdinacijaController {

    private final OrdinacijaService ordinacijaService;

    @Autowired
    public OrdinacijaController(OrdinacijaService ordinacijaService) {
        this.ordinacijaService = ordinacijaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Ordinacija>> getAllOrdinacije() {
        List<Ordinacija> ordinacije = ordinacijaService.getAllOrdinacije();
        return new ResponseEntity<>(ordinacije, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ordinacija>> getOrdinacijaById(@PathVariable Long id) {
        Optional<Ordinacija> ordinacija = ordinacijaService.getOrdinacijaById(id);
        if (ordinacija.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordinacija, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Ordinacija> createOrdinacija(@RequestBody Ordinacija ordinacija) {
        Ordinacija createdOrdinacija = ordinacijaService.createOrdinacija(ordinacija);
        return new ResponseEntity<>(createdOrdinacija, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdinacija(@PathVariable Long id) {
        ordinacijaService.deleteOrdinacija(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


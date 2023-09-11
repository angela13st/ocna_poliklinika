package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Termini;
import com.example.ocna_poliklinika.services.TerminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/termini")
public class TerminiController {

    private final TerminiService terminiService;

    @Autowired
    public TerminiController(TerminiService terminiService) {
        this.terminiService = terminiService;
    }

    @GetMapping("/")
    public List<Termini> sviTermini() {
        return terminiService.sviTermini();
    }

    @GetMapping("/{id}")
    public Optional<Termini> dohvatiTerminPoId(@PathVariable Long id) {
        return terminiService.dohvatiTerminPoId(id);
    }

    @PostMapping("/")
    public Termini spremanjeTermina(@RequestBody Termini termini) {
        return terminiService.spremanjeTermina(termini);
    }

    @DeleteMapping("/{id}")
    public void brisanjeTermina(@PathVariable Long id) {
        terminiService.brisanjeTermina(id);
    }

    @GetMapping("/pacijent/{pacijentId}")
    public List<Termini> terminiZaPacijenta(@PathVariable Long pacijentId) {
        return terminiService.dohvatiTerminiZaPacijenta(pacijentId);
    }

    @GetMapping("/doktor/{doktorId}")
    public List<Termini> terminiZaDoktora(@PathVariable Long doktorId) {
        return terminiService.dohvatiTerminiZaDoktora(doktorId);
    }
}

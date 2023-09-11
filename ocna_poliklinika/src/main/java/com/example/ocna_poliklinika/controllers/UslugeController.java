package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Usluge;
import com.example.ocna_poliklinika.services.UslugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/usluge")
public class UslugeController {

    private final UslugeService uslugeService;

    @Autowired
    public UslugeController(UslugeService uslugeService) {
        this.uslugeService = uslugeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Usluge>> sveUsluge() {
        List<Usluge> usluge = uslugeService.sveUsluge();
        if (usluge.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if no services are found
        }
        return new ResponseEntity<>(usluge, HttpStatus.OK);
    }

    @GetMapping("/sve")
    public String sveUsluge(Model model) {
        List<Usluge> usluge = uslugeService.sveUsluge();
        model.addAttribute("usluge", usluge);
        return "usluge";
    }

    @GetMapping("/{id}")
    public String dohvatiUsluguPoId(@PathVariable Long id, Model model) {
        Optional<Usluge> usluga = uslugeService.dohvatiUsluguPoId(id);
        if (usluga.isPresent()) {
            model.addAttribute("usluga", usluga.get());
        }
        return "detalji_usluge";
    }

    @GetMapping("/nova")
    public String prikaziFormuZaNovuUslugu(Model model) {
        model.addAttribute("usluga", new Usluge());
        return "forma_usluge";
    }

    @PostMapping("/nova")
    public String spremiNovuUslugu(@ModelAttribute("usluga") Usluge usluga) {
        uslugeService.spremanjeUsluge(usluga);
        return "redirect:/usluge/";
    }

    @GetMapping("/uredi/{id}")
    public String prikaziFormuZaUredivanjeUsluge(@PathVariable Long id, Model model) {
        Optional<Usluge> usluga = uslugeService.dohvatiUsluguPoId(id);
        if (usluga.isPresent()) {
            model.addAttribute("usluga", usluga.get());
            return "forma_usluge";
        } else {
            return "redirect:/usluge/";
        }
    }

    @PostMapping("/uredi/{id}")
    public String spremiUređenuUslugu(@PathVariable Long id, @ModelAttribute("usluga") Usluge usluga) {
        uslugeService.spremanjeUsluge(usluga);
        return "redirect:/usluge/";
    }

    @GetMapping("/brisi/{id}")
    public String brisanjeUsluge(@PathVariable Long id) {
        uslugeService.brisanjeUsluge(id);
        return "redirect:/usluge/";
    }

    @PostMapping("/")
    public ResponseEntity<Usluge> spremiUslugu(@RequestBody Usluge usluge) {
        Usluge novaUsluga = uslugeService.spremiNovuUslugu(usluge);
        return new ResponseEntity<>(novaUsluga, HttpStatus.CREATED);
    }

}

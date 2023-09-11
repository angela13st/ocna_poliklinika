package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.services.PacijentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private PacijentService pacijentService;

    @GetMapping
    public String prikaziProfil(Model model, Principal principal) {
        String username = principal.getName();
        Optional<Pacijent> pacijent = pacijentService.dohvatiPacijentaPoUsername(username);
        model.addAttribute("pacijent", pacijent);
        return "profil";
    }

    @PostMapping
    public String azurirajProfil(@ModelAttribute("pacijent") @Valid Pacijent pacijent, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "profil";
        }
        pacijentService.azurirajPacijenta(pacijent);
        redirectAttributes.addFlashAttribute("uspjesnoAzuriranje", true);
        return "redirect:/profil";
    }

    @GetMapping("/profil")
    public String pregledajProfil(Model model) {
        // Ovdje dodajte logiku za dohvat podataka o profilu pacijenta i preglede
        // Na primjer, možete dohvatiti podatke iz baze podataka ili izvući iz sesije.
        // Postavite podatke u model kako biste ih mogli prikazati na stranici Profil.html.
        return "Profil"; // Vraća ime HTML šablona za pregled profila (Profil.html).
    }
}


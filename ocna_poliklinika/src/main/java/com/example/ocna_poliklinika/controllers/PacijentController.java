package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.services.PacijentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/api/pacijenti")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    // Display the patient registration form
    @GetMapping("/registracija")
    public String prikaziFormuRegistracije(Model model) {
        model.addAttribute("pacijent", new Pacijent());
        return "registracija";
    }

    @GetMapping("/")
    public ResponseEntity<List<Pacijent>> sviPacijenti() {
        List<Pacijent> pacijenti = pacijentService.sviPacijenti();
        return new ResponseEntity<>(pacijenti, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> registrirajPacijenta(@RequestBody Pacijent pacijent) {
        try {
            Pacijent spremljeniPacijent = pacijentService.registrirajPacijenta(pacijent);
            return ResponseEntity.ok(spremljeniPacijent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greška prilikom registracije pacijenta: " + e.getMessage());
        }
    }


    // Handle patient registration
    @PostMapping("/registracija")
    public String registrirajPacijenta(
            @ModelAttribute("pacijent") @Valid Pacijent pacijent,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the registration form with error messages
            return "registracija";
        }

        // Check if the email is already registered
        if (pacijentService.emailPostoji(pacijent.getEmail())) {
            bindingResult.rejectValue("email", "error.pacijent", "Email adresa već postoji");
            return "registracija";
        }

        // Save the patient in the database
        pacijentService.registrirajPacijenta(pacijent);

        // Set a success registration message for display on the next page
        redirectAttributes.addFlashAttribute("uspjesnaRegistracija", true);

        return "redirect:/api/pacijenti/uspesnaRegistracija";
    }

    // Display the successful registration page
    @GetMapping("/uspesnaRegistracija")
    public String uspesnaRegistracija(Model model) {
        return "uspesna_registracija";
    }

    // Add other methods and functionalities as needed
}

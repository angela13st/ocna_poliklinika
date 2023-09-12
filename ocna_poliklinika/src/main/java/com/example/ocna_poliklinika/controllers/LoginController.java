package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.repositories.DoktorRepository;
import com.example.ocna_poliklinika.repositories.PacijentRepository;
import com.example.ocna_poliklinika.services.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("loggedInUserId") // Add this annotation to declare session attribute
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private PacijentService pacijentService;

    @GetMapping
    public String prikaziFormuZaLogiranje() {
        return "login";
    }

    @GetMapping("/getLozinka")
    public ResponseEntity<String> getLozinka(@RequestParam String username) {
        PacijentRepository pacijentRepository = null;
        Pacijent pacijent = pacijentRepository.findByUsername(username);
        if (pacijent != null) {
            return ResponseEntity.ok(pacijent.getLozinka());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public String prijava(Model model, String username, String lozinka) {
        // Perform authentication, and if successful:
        if (pacijentService.prijava(username, lozinka)) {
            // Retrieve the patient ID from the service (adjust this based on your service)
            //Long patientId = pacijentService.findPatientIdByUsername(username);

            // Store the patient ID in the session
            //model.addAttribute("loggedInUserId", patientId);

            return "redirect:/narucivanje"; // Redirect to the desired page
        } else {
            model.addAttribute("error", "Pogrešno korisničko ime ili lozinka.");
            return "login";
        }
    }
}


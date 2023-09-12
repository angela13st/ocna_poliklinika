package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.services.DoktorService;
import com.example.ocna_poliklinika.services.PacijentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("loggedInUserId") // Add this annotation to declare session attribute
@RequestMapping("/logindr")
public class LogindrController {

    @Autowired
    private DoktorService doktorService;

    @GetMapping
    public String prikaziFormuZaLogiranjeDr() {
        return "logindr";
    }

    public String prijava(Model model, String username, String password) {
        // Perform authentication, and if successful:
        if (doktorService.prijava(username, password)) {
            // Retrieve the patient ID from the service (adjust this based on your service)
            //Long patientId = pacijentService.findPatientIdByUsername(username);

            // Store the patient ID in the session
            //model.addAttribute("loggedInUserId", patientId);

            return "redirect:/doktorraspored"; // Redirect to the desired page
        } else {
            model.addAttribute("error", "Pogrešno korisničko ime ili lozinka.");
            return "logindr";
        }
    }
}


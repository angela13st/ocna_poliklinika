package com.example.ocna_poliklinika.controllers;

import com.example.ocna_poliklinika.dto.UserLoginRequest;
import com.example.ocna_poliklinika.models.Pacijent;
import com.example.ocna_poliklinika.models.Termini;
import com.example.ocna_poliklinika.services.PacijentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pacijent")
public class PacijentController {

    @Autowired
    private PacijentService pacijentService;

    // Prikaz stranice Pacijent.html
    /*@GetMapping("/profil")
    public String prikaziProfil(Model model) {

    }*/

    // Omogućava izmjenu profila
    @PostMapping("/izmjeniProfil")
    public String izmjeniProfil(@ModelAttribute Pacijent pacijent) {
        // Spremite izmjene u bazu podataka
        pacijentService.azurirajPacijenta(pacijent);

        return "redirect:/pacijent/profil"; // Preusmjerite korisnika natrag na stranicu Pacijent.html
    }

    // Add a method to retrieve a patient by username
    @GetMapping("/username/{username}")
    public ResponseEntity<Pacijent> dohvatiPacijentaPoUsername(@PathVariable String username) {
        Optional<Pacijent> pacijent = pacijentService.dohvatiPacijentaPoUsername(username);
        return pacijent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/login")
    public ResponseEntity<Pacijent> loginUser(@RequestBody UserLoginRequest loginRequest, HttpSession session) {
        Pacijent loggedInUser = pacijentService.loginUser(loginRequest);
        if (loggedInUser != null) {
            // Store the logged-in user in the session
            session.setAttribute("user", loggedInUser);
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

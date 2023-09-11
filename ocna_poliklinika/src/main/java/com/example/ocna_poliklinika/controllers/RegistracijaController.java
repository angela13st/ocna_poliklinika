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

@Controller
@RequestMapping("/register")
public class RegistracijaController {

    @Autowired
    private PacijentService pacijentService;

    @GetMapping
    public String prikaziFormuRegister(Model model) {
        model.addAttribute("pacijent", new Pacijent());
        return "register";
    }

    @PostMapping
    public String registrirajPacijenta(@ModelAttribute("pacijent") @Valid Pacijent pacijent, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        pacijentService.registrirajPacijenta(pacijent);
        redirectAttributes.addFlashAttribute("uspjesnaRegistracija", true);
        return "redirect:/login";
    }

    @GetMapping("/registracijauspjesna")
    public String registracijauspjesna() {
        return "redirect:/login";
    }
}


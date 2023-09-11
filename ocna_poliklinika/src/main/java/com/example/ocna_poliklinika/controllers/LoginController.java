package com.example.ocna_poliklinika.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String prikaziFormuZaLogiranje() {
        return "login";
    }

    @GetMapping("/logiranjeuspjesno")
    public String logiranjeuspjesno(){
        return "redirect:/narucivanje";
    }
}
